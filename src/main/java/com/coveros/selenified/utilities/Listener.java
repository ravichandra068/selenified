/*
 * Copyright 2018 Coveros, Inc.
 *
 * This file is part of Selenified.
 *
 * Selenified is licensed under the Apache License, Version
 * 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy
 * of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package com.coveros.selenified.utilities;

import com.coveros.selenified.Browser;
import com.coveros.selenified.OutputFile.Result;
import com.coveros.selenified.services.HTTP;
import com.coveros.selenified.services.Request;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.SkipException;
import org.testng.TestListenerAdapter;
import org.testng.log4testng.Logger;

import java.io.File;

/**
 * Appends additional test links and information into the TestNG report file,
 * for easier tracking and viewing of detailed custom test reports. This class
 * should be specified as a listener for the main Selenified class, and/or in
 * the TestNG xml file.
 *
 * @author Max Saperstone
 * @version 3.0.4
 * @lastupdate 8/28/2018
 */
public class Listener extends TestListenerAdapter {

    private static final Logger log = Logger.getLogger(Listener.class);

    private static final String BROWSER_INPUT = "browser";
    private static final String OUTPUT_BREAK = " | ";
    private static final String FILE_EXTENTION = "html";
    private static final String LINK_START = "<a target='_blank' href='";
    private static final String LINK_MIDDLE = "." + FILE_EXTENTION + "'>";
    private static final String LINK_END = "</a>";
    private static final String TIME_UNIT = " seconds";

    @Override
    public void onTestStart(ITestResult result) {
        super.onTestStart(result);
        // if a group indicates an invalid browser, skip the test
        Browser browser = (Browser) result.getAttribute(BROWSER_INPUT);
        if (browser != null) {
            String[] groups = result.getMethod().getGroups();
            for (String group : groups) {
                if (group.toLowerCase().equals("no-" + browser.getName().toString().toLowerCase())) {
                    log.warn("Skipping test case " + getTestName(result) + ", as it is not intended for browser " + browser.getName());
                    result.setStatus(ITestResult.SKIP);
                    throw new SkipException("Skipping test case");
                }
            }
        }
    }

    /**
     * determines the folder name associated with the given tests
     *
     * @param result - the testng itestresult object
     * @return String: a string version of the folder name
     */
    private static String getFolderName(ITestResult result) {
        return new File(result.getTestContext().getOutputDirectory()).getName();
    }

    /**
     * determines the test name associated with the given tests
     *
     * @param result - the testng itestresult object
     * @return String: a string version of the test name
     */
    private static String getTestName(ITestResult result) {
        String className;
        String packageName = "";
        if (result.getTestClass().toString().contains(".")) {
            packageName = result.getTestClass().toString().substring(22, result.getTestClass().toString().length() - 1)
                    .split("\\.")[0];
            className = result.getTestClass().toString().substring(22, result.getTestClass().toString().length() - 1)
                    .split("\\.")[1];
        } else {
            className = result.getTestClass().toString().substring(22, result.getTestClass().toString().length() - 1);
        }
        return TestSetup.getTestName(packageName, className, result.getName(), result.getParameters());
    }

    /**
     * Runs the default TestNG onTestFailure, and adds additional information
     * into the testng reporter
     *
     * @param result - the testng itestresult object
     */
    @Override
    public void onTestFailure(ITestResult result) {
        super.onTestFailure(result);
        recordResult(result);
    }

    /**
     * Runs the default TestNG onTestSkipped, and adds additional information
     * into the testng reporter
     *
     * @param result - the testng itestresult object
     */
    @Override
    public void onTestSkipped(ITestResult result) {
        super.onTestSkipped(result);
        recordResult(result);
    }

    /**
     * Runs the default TestNG onTestSuccess, and adds additional information
     * into the testng reporter
     *
     * @param result - the testng itestresult object
     */
    @Override
    public void onTestSuccess(ITestResult result) {
        super.onTestSuccess(result);
        recordResult(result);
    }

    /**
     * Checks to see if the test execution was performed on SauceLabs or not. If it was, this reaches out to Sauce,
     * in order to update the execution results
     *
     * @param result - the testng itestresult object
     */
    private void recordResult(ITestResult result) {
        String testName = getTestName(result);
        Browser browser = (Browser) result.getAttribute(BROWSER_INPUT);
        if (browser != null) {
            Reporter.log(
                    Result.values()[result.getStatus()] + OUTPUT_BREAK + browser.getName() + OUTPUT_BREAK + LINK_START +
                            getFolderName(result) + "/" + testName + browser.getName() + LINK_MIDDLE + testName +
                            LINK_END + OUTPUT_BREAK + (result.getEndMillis() - result.getStartMillis()) / 1000 + TIME_UNIT);
        }
        if (Sauce.isSauce() && result.getAttributeNames().contains("SessionId")) {
            String sessionId = result.getAttribute("SessionId").toString();
            JsonObject json = new JsonObject();
            json.addProperty("passed", result.getStatus() == 1);
            JsonArray tags = new JsonArray();
            for (String tag : result.getMethod().getGroups()) {
                tags.add(tag);
            }
            json.add("tags", tags);
            HTTP http =
                    new HTTP("https://saucelabs.com/rest/v1/" + Sauce.getSauceUser() + "/jobs/", Sauce.getSauceUser(),
                            Sauce.getSauceKey());
            http.put(sessionId, new Request().setJsonPayload(json), null);
        }
    }
}
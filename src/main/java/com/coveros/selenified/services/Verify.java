/*
 * Copyright 2019 Coveros, Inc.
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

package com.coveros.selenified.services;

import com.coveros.selenified.utilities.Reporter;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.Map;

/**
 * Verify will handle all verifications performed on the actual web services
 * calls themselves. These asserts are custom to the framework, and in addition to
 * providing easy object oriented capabilities, they assist in
 * troubleshooting and debugging failing tests.
 *
 * @author Max Saperstone
 * @version 3.2.0
 * @lastupdate 4/4/2019
 */
public class Verify implements Check {


    // this will be the name of the file we write all commands out to
    private final Reporter reporter;

    // this is the driver that will be used for all selenium actions
    private final Response response;

    /**
     * The default constructor passing in the app and output file
     *
     * @param response - the response from the web services call
     * @param reporter - the file to write all logging out to
     */
    public Verify(Response response, Reporter reporter) {
        this.response = response;
        this.reporter = reporter;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Reporter getReporter() {
        return reporter;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Response getResponse() {
        return response;
    }

    ///////////////////////////////////////////////////////
    // assertions about the page in general
    ///////////////////////////////////////////////////////

    /**
     * Verifies the actual response code is equals to the expected response
     * code, and writes that out to the output file.
     *
     * @param expectedCode - the expected response code
     */
    @Override
    @SuppressWarnings("squid:S1201")
    public void equals(int expectedCode) {
        checkEquals(expectedCode);
    }

    /**
     * Verifies the actual response json payload is equal to the expected
     * response json payload, and writes that out to the output file.
     *
     * @param expectedJson - the expected response json object
     */
    @Override
    @SuppressWarnings("squid:S1201")
    public void equals(JsonObject expectedJson) {
        checkEquals(expectedJson);
    }

    /**
     * Verifies the actual response json payload is equal to the expected
     * response json payload, and writes that out to the output file.
     *
     * @param expectedJson - the expected response json array
     */
    @Override
    @SuppressWarnings("squid:S1201")
    public void equals(JsonArray expectedJson) {
        checkEquals(expectedJson);
    }

    /**
     * Verifies the actual response payload is equal to the expected
     * response payload, and writes that out to the output file.
     *
     * @param expectedMessage - the expected response message
     */
    @Override
    @SuppressWarnings("squid:S1201")
    public void equals(String expectedMessage) {
        checkEquals(expectedMessage);
    }

    /**
     * Verifies the actual response json payload contains each of the pair
     * values provided, and writes that to the output file.
     *
     * @param expectedPairs a hashmap with string key value pairs expected in the json
     *                      response
     */
    @Override
    public void contains(Map<String, Object> expectedPairs) {
        checkContains(expectedPairs);
    }

    /**
     * Verifies the actual response json payload contains to the expected json
     * element, and writes that out to the output file.
     *
     * @param expectedJson - the expected response json array
     */
    @Override
    public void contains(JsonElement expectedJson) {
        checkContains(expectedJson);
    }

    /**
     * Verifies the actual response json payload contains to the expected json
     * element, and writes that out to the output file.
     *
     * @param expectedMessage - the expected response json array
     */
    @Override
    public void contains(String expectedMessage) {
        checkContains(expectedMessage);
    }
}
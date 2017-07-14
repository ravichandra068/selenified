/*
 * Copyright 2017 Coveros, Inc.
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

package com.coveros.selenified.selenium;

import com.coveros.selenified.selenium.Selenium.Locator;

public class Element {

    private Locator type;
    private String locator;

    public Element(Locator type, String locator) {
        this.setType(type);
        this.setLocator(locator);
    }

    public void setType(Locator type) {
        this.type = type;
    }

    public void setLocator(String locator) {
        this.locator = locator;
    }

    public Locator getType() {
        return type;
    }

    public String getLocator() {
        return locator;
    }
}
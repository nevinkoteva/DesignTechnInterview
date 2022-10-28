package com.clippings.test.runners;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = "src/test/resources/features", glue = {
        "com.clippings.test.stepdefinitions"}, monochrome = true, plugin = {"pretty", "html:target/cucumber-html-report",
        "json:target/cucumber-json", "junit:target/cucumber-junit"})

public class ClippingsTestRunner extends AbstractTestNGCucumberTests {

}

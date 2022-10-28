package com.clippings.test.stepdefinitions;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class BaseSteps {
    private static final int WAIT_TIME_FIFTEEN_SECONDS = 15;
    WebDriver driver = null;
    private static final Logger logger = LoggerFactory.getLogger(BaseSteps.class);

    public WebDriver getDriver() {
        return driver;
    }

    @Before
    public void startDriver() {
        logger.info("Starting chrome...");
        ChromeOptions options = new ChromeOptions();
        WebDriverManager.getInstance(ChromeDriver.class).setup();
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(WAIT_TIME_FIFTEEN_SECONDS, TimeUnit.SECONDS);
    }

    @After
    public void tearDownTest(Scenario scenario) {
       // if (scenario.isFailed()) {
            // Take a screenshot...
            final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            // embed it in the report.
            scenario.embed(screenshot, "image/png");
       // }

        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

}

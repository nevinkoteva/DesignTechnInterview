package com.clippings.test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverUtilityMethods {

    /**
     * Wait page to load
     *
     * @param element WebElement
     * @param seconds seconds which should be waited for element
     */
    public static void waitForPageLoad(WebDriver driver, WebElement element, int seconds) {

        WebDriverWait wait = new WebDriverWait(driver, seconds);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

}

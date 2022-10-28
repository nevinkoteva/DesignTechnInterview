package com.clippings.test.pageobjects;

import com.clippings.test.CurrencyEnum;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import java.util.List;

public class ProductsPO {
    protected final WebDriver driver;
    private static final Logger logger = LoggerFactory.getLogger(ProductsPO.class);
    private static final String DATA_TESTID_PRICE_XPATH = "//*[@data-testid='component-price-regular']";
    private static final String CURRENCY_VALUE_OPTION = "//*[@data-value='%s']";
    private static final String PRODUCTS_LIST_XPATH = "//*[contains(@data-testid,'product-tile-container')]";

    @FindBy(how = How.XPATH, using = "//*[@class='main-header__search__input']/input")
    private WebElement searchField;
    @FindBy(how = How.XPATH, using = "//*[contains(@class,'btn-filters')]")
    private WebElement filterButton;

    @FindBy(how = How.ID, using = "mui-component-select-currency")
    private WebElement currencyConfig;

    @FindBy(how = How.XPATH, using = "//*[@data-value='%s']")
    private WebElement currencyValueOption;

    public ProductsPO(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    public void searchByText(String text) {

    }

    public void openSortFilterMenu() {
        filterButton.click();
    }

    public void verifyPricesOfProductsAreWithinRange(int min, int max) throws InterruptedException {
        Thread.sleep(1000);

        List<WebElement> products = driver.findElements(By.xpath(PRODUCTS_LIST_XPATH));

        for (WebElement product : products) {
            String price = product.findElement(By.xpath(DATA_TESTID_PRICE_XPATH)).getText();
            price = price.substring(1);
            logger.info("price is {}", price);
            Double doublePrice = Double.parseDouble(price);

            Assert.assertTrue(doublePrice >= min && doublePrice <= max);
        }
    }

    public void verifyCurrencyOfPrices(String currency) throws InterruptedException {
        Thread.sleep(1000);

        List<WebElement> products = driver.findElements(By.xpath(PRODUCTS_LIST_XPATH));

        for (WebElement product : products) {
            String price = product.findElement(By.xpath(DATA_TESTID_PRICE_XPATH)).getText();
            String currencySymbol = Character.toString(price.charAt(0));
            logger.info("currency is {}", currencySymbol);

            Assert.assertEquals(currencySymbol, CurrencyEnum.valueOf(currency).getCurrencySymbol());
        }
    }

    public String getConfiguredCurrency() {
        return currencyConfig.getText();
    }

    public void changeCurrency(String currency) {
        if (!CurrencyEnum.valueOfEnum(getConfiguredCurrency()).equals(currency)) {
            logger.info("Selecting new currency {}", currency);
            currencyConfig.click();
            driver.findElement(By.xpath(String.format(CURRENCY_VALUE_OPTION, currency))).click();
        } else logger.info("Currency {} is already selected. Nothing to do.", currency);
    }
}
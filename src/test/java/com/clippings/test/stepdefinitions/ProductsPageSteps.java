package com.clippings.test.stepdefinitions;

import com.clippings.test.pageobjects.ProductsPO;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.asserts.SoftAssert;

public class ProductsPageSteps {
    private static final Logger logger = LoggerFactory.getLogger(ProductsPageSteps.class);
    private final WebDriver driver;
    private ProductsPO products;

    public ProductsPageSteps(BaseSteps baseSteps) {
        this.driver = baseSteps.getDriver();
        this.products = new ProductsPO(driver);

    }

    @When("user selects currency (.*)")
    public void changeCurrency(String currency) {
        products.changeCurrency(currency);
    }

    @Then("verify product list contains only items priced between {int} and {int}")
    public void verifyPricesOfProducts(int min, int max) throws InterruptedException {
        products.verifyPricesOfProductsAreWithinRange(min, max);

    }

    @Then("verify items are listed in (.*)")
    public void verifyResultsCurrency(String currency) throws InterruptedException {
        if (products.getConfiguredCurrency().contains(currency)) {
            products.verifyCurrencyOfPrices(currency);
        } else logger.info("Change currency");
    }


}

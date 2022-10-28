package com.clippings.test.stepdefinitions;

import com.clippings.test.ClippingsAppConfig;
import com.clippings.test.base.BaseTest;
import com.clippings.test.pageobjects.ProductsPO;
import com.clippings.test.pageobjects.SortFilterPO;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import io.cucumber.datatable.DataTable;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.asserts.SoftAssert;

public class SortFilterSteps {
    private static final Logger logger = LoggerFactory.getLogger(SortFilterSteps.class);
    final ClippingsAppConfig configuration;

    private final WebDriver driver;
    private SortFilterPO sortFilterPO;
   private ProductsPO products;

    public SortFilterSteps(BaseSteps baseSteps) {
        this.configuration = BaseTest.getConfiguration();
        this.driver = baseSteps.getDriver();
        this.sortFilterPO = new SortFilterPO(driver);
        this.products = new ProductsPO(driver);
    }

    @Given("user is on the Clippings Products search page")
    public void navigateToProductsSearchPage() {
        if (!driver.getCurrentUrl().contains(configuration.getBaseUrl())) {
            driver.navigate().to(configuration.getBaseUrl());
        }
    }

    @Given("filter menu is opened")
    public void openFilterMenu() {
        //check if filter menu is already displayed and open it if not
        if (!sortFilterPO.isSortFilterMenuDisplayed()) {
            products.openSortFilterMenu();
        }
    }

    @When("user selects (.*) from the hierarchical menu")
    public void selectItemFromHierarchicalMenu(String item) {
        sortFilterPO.selectItemFromHierarchicalMenu(item);
    }

    @When("user selects the following price filters")
    public void selectPriceFilters(DataTable range) {
        sortFilterPO.selectPriceRange(range);
    }

}

package com.clippings.test.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.cucumber.datatable.DataTable;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SortFilterPO extends ProductsPO {
    private static final String HIERARCHICAL_MENU_LABEL = "//*[contains(@class,'ais-HierarchicalMenu-list')]//*[text()='%s']";
    private static final String HIERARCHICAL_MENU_ITEMS_COUNT = HIERARCHICAL_MENU_LABEL + "/following-sibling::*";
    private static final Logger logger = LoggerFactory.getLogger(SortFilterPO.class);

    @FindBy(how = How.ID, using = "filter")
    private WebElement filterSidebar;

    @FindBy(how = How.CLASS_NAME, using = "ais-RangeInput-inputMin")
    private WebElement rangeInputMin;

    @FindBy(how = How.CLASS_NAME, using = "ais-RangeInput-inputMax")
    private WebElement rangeInputMax;

    @FindBy(how = How.XPATH, using = "//*[@data-title='Price']//*[@class='ais-RangeInput-submit']")
    private WebElement priceRangeInputSubmit;

    public SortFilterPO(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void selectItemFromHierarchicalMenu(String hierarchicalMenuLabel) {
        logger.info("Select {} from the hierarchical menu", hierarchicalMenuLabel);
        driver.findElement(By.xpath(String.format(HIERARCHICAL_MENU_LABEL, hierarchicalMenuLabel))).click();
        //TODO: add check if hierarchical item exists in the menu
    }

    public void selectPriceRange(DataTable dataTable) {

        List<List<String>> data = dataTable.asLists()
                .stream()
                .map(ArrayList::new)
                .collect(Collectors.toList());
        data.remove(0);
        List<String> flattenData = data.stream()
                .flatMap(List::stream)
                .collect(Collectors.toList());

        logger.debug("Filter products in price range between {} and {}", flattenData.get(0), flattenData.get(1));
        rangeInputMin.sendKeys(flattenData.get(0));
        rangeInputMax.sendKeys(flattenData.get(1));
        priceRangeInputSubmit.click();

    }

    public void selectMinPrice(String minPrice) {
        logger.debug("Filter products by max price");
        rangeInputMin.sendKeys(minPrice);
        priceRangeInputSubmit.click();

    }

    public void selectMaxPrice(String maxPrice) {
        logger.debug("Filter products by max price");
        rangeInputMax.sendKeys(maxPrice);
        priceRangeInputSubmit.click();
    }

    public int getProductsListCount(String hierarchicalMenuLabel) {

        WebElement productsCount = driver.findElement(By.xpath(String.format(HIERARCHICAL_MENU_ITEMS_COUNT, hierarchicalMenuLabel)));
        return Integer.parseInt(productsCount.getText());
    }

    public boolean isSortFilterMenuDisplayed() {
        return filterSidebar.isDisplayed();
    }


}
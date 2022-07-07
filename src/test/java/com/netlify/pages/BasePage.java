package com.netlify.pages;


import com.netlify.managers.Driver;
import com.netlify.managers.PageObjectManager;
import com.netlify.utilities.BrowserUtils;
import com.netlify.utilities.ConfigurationReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

public class BasePage {
    PageObjectManager pg = new PageObjectManager();
    WebDriver driver = Driver.getDriver();
    By productBy = By.cssSelector("div.ProductCardstyle__ProductCardWrapper-sc-5v39a6-0.dVEpkp");
    By addToCart = By.xpath("//button[text()='Add to Cart']");
    By pageBy = By.xpath("//li[contains(@class,'PaginationIndicatorstyle__Li-urm45v-1') and not(@disabled)]");

    public void setFilter(String label) {
        driver.findElements(filter)
                .stream()
                .filter(el -> el.getAttribute("label")
                        .equalsIgnoreCase(label))
                .collect(Collectors.toList())
                .get(0)
                .click();
    }

    By filter = By.cssSelector("div.Checkboxstyle__CheckboxWrapper-sc-4niyhm-1.fUmiCk > input");

    public BasePage startApp() {
        driver.get(ConfigurationReader.getProperty("url"));
        return this;
    }

    public List<WebElement> getProducts() {
        return driver.findElements(productBy);
    }

    public WebElement hoverOverProduct(int index) {
        WebElement product = driver.findElements(productBy).get(index-1);
        BrowserUtils.hoverOver(product);
        return product;
    }

    public WebElement addToCart(WebElement element) {
        WebElement addToCardElement = BrowserUtils.waitForClickability(element.findElement(By.tagName("button")));
       addToCardElement.click();
        // BrowserUtils.clickWithJS(addToCardElement);
        return addToCardElement;
    }

    public BasePage gotoPage(String page) {
        BrowserUtils.waitForClickability(pageBy);
        List<WebElement> pages = driver.findElements(pageBy);
        int index;
        switch (page) {
            case "first":
                index = 0;
                break;
            case "last":
                index = pages.size() - 2;
                break;
            default:
                index = Integer.parseInt(page) - 1;
        }
        pages.get(index).click();
        return this;
    }
}

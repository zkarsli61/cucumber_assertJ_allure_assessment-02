package com.netlify.step_definitions;

import com.netlify.managers.PageObjectManager;
import com.netlify.pages.BasePage;
import com.netlify.utilities.BrowserUtils;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
//import org.junit.Assert;

public class BaseStepDefinitions {

    PageObjectManager pg = new PageObjectManager();
    BasePage basePage = pg.getBasePage();
    WebElement curProduct;
    Dimension defaultSize;

    @Given("user is on the application")
    public void user_is_on_the_app() {
        basePage.startApp();
    }

    @Then("verify that The product list should contain {int} artworks")
    public void verify_page(int numberOfProduct) {

        Assertions.assertThat(basePage.getProducts().size())
                .as("product list should contain " + numberOfProduct + " artworks")
                .isEqualTo(numberOfProduct);

    }

    @Then("hover over {int} th product")
    public void hoverOverProduct(int index) {
        curProduct = basePage.hoverOverProduct(index);
    }

    @Given("Click Add to Cart {int} times")
    public void user_click_sign_in(int times) {

        try {
            for (int i = 0; i < times; i++) {
                basePage.addToCart(curProduct);
            }
        } catch (Exception e) {
            Assertions.assertThat(false)
                    .as("Add to Cart should be visible  when hovering over the product")
                    .isTrue();
        }
    }

    @Then("go to {string} page")
    public void goToLastPage(String page) {
        basePage.gotoPage(page);
    }

    @And("check whether product image size is cropped or not")
    public void checkWhetherProductImageSizeIsCroppedOrNot() {
        Assertions.assertThat(basePage.getProducts()
                .get(0)
                .getSize().toString())
                .isEqualTo(defaultSize.toString());
    }

    @And("get default image size")
    public void getDefaultImageSize() {
        defaultSize = basePage.getProducts().get(0).getSize();
    }

    @Then("choose {string} for filtering")
    public void chooseAsCategory(String label) {
        basePage.setFilter(label);
    }
}
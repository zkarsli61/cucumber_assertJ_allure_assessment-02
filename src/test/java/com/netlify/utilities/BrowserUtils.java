package com.netlify.utilities;

import com.netlify.managers.Driver;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;

import java.util.ArrayList;
import java.util.List;

public class BrowserUtils {


    public static WebElement waitForPresence(By element) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), 20);
        return wait.until(ExpectedConditions.presenceOfElementLocated(element));
    }

    public static boolean waitForUrlChanged(String endsWith) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), 30);
        ExpectedCondition<Boolean> urlHasChanged = x-> !x.getCurrentUrl().endsWith(endsWith);
        Boolean isChanged = wait.until(urlHasChanged);
        waitForPageToLoad();
        return isChanged;
    }

    public static WebElement waitForClickability(By element) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), 30);
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public static WebElement waitForClickability(WebElement element) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), 30);
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }
    public static WebElement waitForVisible(WebElement element) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), 30);
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    public static void hoverOver(WebElement element) {
        Actions actions = new Actions(Driver.getDriver());
        actions.moveToElement(element).build().perform();
    }

    public static boolean verifyPage(By element,String page)  {
        boolean success=true;
        try {
            BrowserUtils.waitForPresence(element);
        } catch (Exception e) {
            success=false;
        }
        return success;
    }
    public static void clickWithJS(WebElement element) {
        ((JavascriptExecutor) Driver.getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
        ((JavascriptExecutor) Driver.getDriver()).executeScript("arguments[0].click();", element);
    }

    public static void waitForPageToLoad() {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), 20);
        ExpectedCondition<Boolean> expectation = driver -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
        try {
            wait.until(expectation);
        } catch (Throwable error) {
            error.printStackTrace();
        }
    }

    public static void waitForPageTitle(String pageTitle) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), 20);
        wait.until(ExpectedConditions.titleIs(pageTitle));

    }

    public static List<String> getListOfString(List<WebElement> listOfWebElements) {
        List<String> listOfStrings = new ArrayList<>();
        for (WebElement element : listOfWebElements) {
            String value = element.getText().trim();
            if (value.length() > 0) {
                listOfStrings.add(value);
            }
        }
        return listOfStrings;
    }

}
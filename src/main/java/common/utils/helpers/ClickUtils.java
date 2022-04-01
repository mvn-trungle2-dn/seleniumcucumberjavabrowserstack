package common.utils.helpers;

import common.LoggingManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import webui.WebDriverManager;

public class ClickUtils {
    public ClickUtils() {
    }

    public static void click(String className, WebElement element, String errorMessage) {
        try {
            element.click();
        } catch (Exception var4) {
            clickUsingJavaScript(className, element, errorMessage);
        }

    }

    public static void click(String className, By locator, String errorMessage) {
        try {
            WebElement element = WebDriverManager.getWebDriver().findElement(locator);
            element.click();
        } catch (Exception var4) {
            clickUsingJavaScript(className, locator, errorMessage);
        }

    }

    public static void clickUsingJavaScript(String className, WebElement element, String errorMessage) {
        try {
            JavascriptExecutor jsExecutor = (JavascriptExecutor)WebDriverManager.getWebDriver();
            jsExecutor.executeScript("arguments[0].click()", new Object[]{element});
        } catch (Exception var4) {
            LoggingManager.logError(className, errorMessage, var4);
        }

    }

    public static void clickUsingJavaScript(String className, By locator, String errorMessage) {
        try {
            WebElement element = WebDriverManager.getWebDriver().findElement(locator);
            JavascriptExecutor jsExecutor = (JavascriptExecutor)WebDriverManager.getWebDriver();
            jsExecutor.executeScript("arguments[0].click()", new Object[]{element});
        } catch (Exception var5) {
            LoggingManager.logError(className, errorMessage, var5);
        }

    }

    public static void doubleClick(String className, WebElement element, String errorMessage) {
        try {
            Actions actions = new Actions(WebDriverManager.getWebDriver());
            actions.doubleClick(element).perform();
        } catch (Exception var4) {
            LoggingManager.logError(className, errorMessage, var4);
        }

    }

    public static void rightClick(String className, WebElement element, String errorMessage) {
        try {
            Actions actions = new Actions(WebDriverManager.getWebDriver());
            actions.moveToElement(element).perform();
            actions.contextClick(element).perform();
        } catch (Exception var4) {
            LoggingManager.logError(className, errorMessage, var4);
        }

    }

    public static void waitLongUntilClickable(String className, WebElement element, String errorMessage) {
        try {
            WaitUtils.waitLong().ignoring(StaleElementReferenceException.class).until(ExpectedConditions.elementToBeClickable(element));
        } catch (Exception var4) {
            LoggingManager.logError(className, errorMessage, var4);
        }
    }

    public static void waitUntilClickable(String className, WebElement element, String errorMessage) {
        try {
            WaitUtils.waitDefault().ignoring(StaleElementReferenceException.class).until(ExpectedConditions.elementToBeClickable(element));
        } catch (Exception var4) {
            LoggingManager.logError(className, errorMessage, var4);
        }

    }

    public static void waitUntilClickable(String className, By locator, String errorMessage) {
        try {
            WaitUtils.waitDefault().ignoring(StaleElementReferenceException.class).until(ExpectedConditions.elementToBeClickable(locator));
        } catch (Exception var4) {
            LoggingManager.logError(className, errorMessage, var4);
        }

    }
}

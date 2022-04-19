package common.utils.helpers;

import common.LoggingManager;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import webui.WebDriverManager;

public class VisibilityUtils {
    public VisibilityUtils() {
    }

    public static Boolean elementExists(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (Exception var2) {
            return false;
        }
    }

    public static void waitLongUntilVisible(String logger, WebElement element, String errorMessage) {
        try {
            WaitUtils.waitLong().ignoring(StaleElementReferenceException.class).until(ExpectedConditions.visibilityOf(element));
        } catch (Exception var4) {
            LoggingManager.logError(logger, errorMessage, var4);
        }

    }

    public static void waitUntilNotVisible(String logger, WebElement element, String errorMessage) {
        try {
            WaitUtils.waitDefault().ignoring(StaleElementReferenceException.class).until(ExpectedConditions.invisibilityOf(element));
        } catch (Exception var4) {
            LoggingManager.logError(logger, errorMessage, var4);
        }

    }

    public static void waitUntilVisible(String logger, WebElement element, String errorMessage) {
        try {
            WaitUtils.waitDefault().ignoring(StaleElementReferenceException.class).until(ExpectedConditions.visibilityOf(element));
        } catch (Exception var4) {
            LoggingManager.logError(logger, errorMessage, var4);
        }

    }

    public static void waitUntilVisible(String logger, By locator, String errorMessage) {
        try {
            WaitUtils.waitDefault().ignoring(StaleElementReferenceException.class).until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (Exception var4) {
            LoggingManager.logError(logger, errorMessage, var4);
        }

    }
}

package common.utils.helpers;

import common.LoggingManager;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import webui.WebDriverManager;

public class EnabledUtils {
    public EnabledUtils() {
    }

    public static ExpectedCondition<Boolean> elementToBeEnabled(WebElement element) {
        return (driver) -> {
            return element.isEnabled();
        };
    }

    public static void waitUntilEnabled(String logger, WebElement element, String errorMessage) {
        try {
            WaitUtils.waitDefault().ignoring(StaleElementReferenceException.class).until(elementToBeEnabled(element));
        } catch (Exception var4) {
            LoggingManager.logError(logger, errorMessage, var4);
        }

    }


}
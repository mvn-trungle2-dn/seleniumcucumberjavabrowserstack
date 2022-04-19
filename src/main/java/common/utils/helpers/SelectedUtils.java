package common.utils.helpers;

import common.LoggingManager;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SelectedUtils {
    public SelectedUtils() {
    }

    public static void waitUntilSelected(String logger, WebElement element, String errorMessage) {
        try {
            WaitUtils.waitDefault().ignoring(StaleElementReferenceException.class).until(ExpectedConditions.elementToBeSelected(element));
        } catch (Exception var4) {
            LoggingManager.logError(logger, errorMessage, var4);
        }

    }

}

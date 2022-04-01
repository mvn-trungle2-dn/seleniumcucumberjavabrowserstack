package common.utils.helpers;

import common.LoggingManager;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import webui.WebDriverManager;

public class KeyUtils {
    public KeyUtils() {
    }

    public static void pressDown(String logger) {
        try {
            Actions actions = new Actions(WebDriverManager.getWebDriver());
            actions.sendKeys(new CharSequence[]{Keys.ARROW_DOWN}).perform();
        } catch (Exception var2) {
            LoggingManager.logError(logger, "Unable to press 'Down'", var2);
        }

    }

    public static void pressEnter(String logger) {
        try {
            Actions actions = new Actions(WebDriverManager.getWebDriver());
            actions.sendKeys(new CharSequence[]{Keys.ENTER}).perform();
        } catch (Exception var2) {
            LoggingManager.logError(logger, "Unable to press 'Enter'", var2);
        }

    }

    public static void pressEscape(String logger) {
        try {
            Actions actions = new Actions(WebDriverManager.getWebDriver());
            actions.sendKeys(new CharSequence[]{Keys.ESCAPE}).perform();
        } catch (Exception var2) {
            LoggingManager.logError(logger, "Unable to press 'Escape'", var2);
        }

    }
}

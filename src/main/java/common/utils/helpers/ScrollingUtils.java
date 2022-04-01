package common.utils.helpers;

import common.LoggingManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import webui.WebDriverManager;

public class ScrollingUtils {
    public ScrollingUtils() {
    }

    public static void scrollIntoView(String logger, WebElement element, boolean alignToTop, int additionalPixels, String errorMessage) {
        try {
            JavascriptExecutor jsExecutor = (JavascriptExecutor) WebDriverManager.getWebDriver();
            jsExecutor.executeScript("arguments[0].scrollIntoView(arguments[1])", new Object[]{element, alignToTop});
            jsExecutor.executeScript("window.scrollBy(0, arguments[0])", new Object[]{additionalPixels});
        } catch (Exception var6) {
            LoggingManager.logError(logger, errorMessage, var6);
        }

    }

    public static void scrollToBottom(String logger, WebElement element, String errorMessage) {
        try {
            JavascriptExecutor jsExecutor = (JavascriptExecutor)WebDriverManager.getWebDriver();
            jsExecutor.executeScript("arguments[0].scrollTop=arguments[0].scrollHeight", new Object[]{element});
        } catch (Exception var4) {
            LoggingManager.logError(logger, errorMessage, var4);
        }
    }

    public static void scrollToBottomPage(){
        try {
            JavascriptExecutor jsExecutor = (JavascriptExecutor)WebDriverManager.getWebDriver();
            jsExecutor.executeScript("window.scrollTo(0,document.body.scrollHeight);");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

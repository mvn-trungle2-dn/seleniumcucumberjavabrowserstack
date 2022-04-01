package common.utils.helpers;

import common.LoggingManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import webui.WebDriverManager;

public class TextUtils {
    public TextUtils() {
    }

    public static void clearText(String logger, WebElement textElement, String errorMessage) {
        try {
            textElement.clear();
        } catch (Exception var4) {
            LoggingManager.logError(logger, errorMessage, var4);
        }
    }

    public static String getText(String logger, WebElement textElement, String errorMessage) {
        String text = null;
        try {
            text = textElement.getText();
        } catch (Exception var5) {
            LoggingManager.logError(logger, errorMessage, var5);
        }
        return text;
    }

    public static String getValue(String logger, WebElement textElement, String errorMessage) {
        String value = null;
        try {
            value = textElement.getAttribute("value");
        } catch (Exception var5) {
            LoggingManager.logError(logger, errorMessage, var5);
        }
        return value;
    }

    private static boolean isTextEmpty(WebElement textElement) {
        return textElement.getText().isEmpty();
    }

    public static void setText(String logger, WebElement textElement, String textValue, String errorMessage) {
        try {
            Actions actions = new Actions(WebDriverManager.getWebDriver());
            actions.sendKeys(textElement, new CharSequence[]{textValue}).perform();
        } catch (Exception var5) {
            LoggingManager.logError(logger, errorMessage, var5);
        }
    }

    public static void setValueUsingJavaScript(String logger, WebElement textElement, String textValue, String errorMessage) {
        try {
            JavascriptExecutor jsExecutor = (JavascriptExecutor)WebDriverManager.getWebDriver();
            jsExecutor.executeScript("arguments[0].setAttribute('value', arguments[1])", new Object[]{textElement, textValue});
        } catch (Exception var5) {
            LoggingManager.logError(logger, errorMessage, var5);
        }
    }

    public static void setValueUsingJavaScript(String logger, By textLocator, String textValue, String errorMessage) {
        try {
            WebElement textElement = WebDriverManager.getWebDriver().findElement(textLocator);
            JavascriptExecutor jsExecutor = (JavascriptExecutor)WebDriverManager.getWebDriver();
            jsExecutor.executeScript("arguments[0].setAttribute('value', arguments[1])", new Object[]{textElement, textValue});
        } catch (Exception var6) {
            LoggingManager.logError(logger, errorMessage, var6);
        }
    }

    public static ExpectedCondition<Boolean> textToBeEmpty(WebElement textElement) {
        return (driver) -> {
            return isTextEmpty(textElement);
        };
    }

    public static ExpectedCondition<Boolean> textToNotBeEmpty(WebElement textElement) {
        return (driver) -> {
            return !isTextEmpty(textElement);
        };
    }

    public static void waitUntilTextContains(String logger, WebElement textElement, String textValue, String errorMessage) {
        try {
            WaitUtils.waitDefault().ignoring(StaleElementReferenceException.class).until(ExpectedConditions.textToBePresentInElement(textElement, textValue));
        } catch (Exception var5) {
            LoggingManager.logError(logger, errorMessage, var5);
        }
    }

    public static void waitUntilTextContains(String logger, By textLocator, String textValue, String errorMessage) {
        try {
            WaitUtils.waitDefault().ignoring(StaleElementReferenceException.class).until(ExpectedConditions.textToBePresentInElementLocated(textLocator, textValue));
        } catch (Exception var5) {
            LoggingManager.logError(logger, errorMessage, var5);
        }
    }

    public static void waitUntilTextIsEmpty(String logger, WebElement textElement, String errorMessage) {
        try {
            WaitUtils.waitDefault().ignoring(StaleElementReferenceException.class).until(textToBeEmpty(textElement));
        } catch (Exception var4) {
            LoggingManager.logError(logger, errorMessage, var4);
        }
    }

    public static void waitUntilTextIsNotEmpty(String logger, WebElement textElement, String errorMessage) {
        try {
            WaitUtils.waitDefault().ignoring(StaleElementReferenceException.class).until(textToNotBeEmpty(textElement));
        } catch (Exception var4) {
            LoggingManager.logError(logger, errorMessage, var4);
        }
    }

}

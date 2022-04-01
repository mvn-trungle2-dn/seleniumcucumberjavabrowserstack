package common.utils.helpers;

import common.LoggingManager;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import webui.WebDriverManager;

import java.util.List;

public class SelectUtils {
    public SelectUtils() {
    }

    public static void deselectAll(String logger, WebElement selectElement, String errorMessage) {
        try {
            Select select = new Select(selectElement);
            select.deselectAll();
        } catch (Exception var4) {
            LoggingManager.logError(logger, errorMessage, var4);
        }

    }

    private static boolean doesSelectContain(Select select, String selectOptionText, Boolean caseSensitive) {
        return caseSensitive ? select.getFirstSelectedOption().getText().contains(selectOptionText) : select.getFirstSelectedOption().getText().toLowerCase().contains(selectOptionText.toLowerCase());
    }

    private static boolean doesSelectEqual(Select select, String selectOptionText, Boolean caseSensitive) {
        return caseSensitive ? select.getFirstSelectedOption().getText().equals(selectOptionText) : select.getFirstSelectedOption().getText().equalsIgnoreCase(selectOptionText);
    }

    public static List<WebElement> getAllSelectedOptions(String logger, WebElement selectElement, String errorMessage) {
        List selectedOptions = null;

        try {
            Select select = new Select(selectElement);
            selectedOptions = select.getAllSelectedOptions();
        } catch (Exception var5) {
            LoggingManager.logError(logger, errorMessage, var5);
        }

        return selectedOptions;
    }

    public static WebElement getFirstSelectedOption(String logger, WebElement selectElement, String errorMessage) {
        WebElement selectedOption = null;

        try {
            Select select = new Select(selectElement);
            selectedOption = select.getFirstSelectedOption();
        } catch (Exception var5) {
            LoggingManager.logError(logger, errorMessage, var5);
        }

        return selectedOption;
    }

    private static boolean isSelectEmpty(Select select) {
        boolean isSelectEmpty = false;

        try {
            isSelectEmpty = select.getAllSelectedOptions().isEmpty();
        } catch (Exception var3) {
            LoggingManager.logError(SelectUtils.class.getSimpleName(), "Unable to verify if select is empty", var3);
        }

        return isSelectEmpty;
    }

    public static void select(String logger, WebElement selectElement, int selectOptionIndex, String errorMessage) {
        try {
            Select select = new Select(selectElement);
            select.selectByIndex(selectOptionIndex);
        } catch (Exception var5) {
            LoggingManager.logError(logger, errorMessage, var5);
        }

    }

    public static void select(String logger, WebElement selectElement, String selectOptionText, String errorMessage) {
        try {
            Select select = new Select(selectElement);
            select.selectByVisibleText(selectOptionText);
        } catch (Exception var5) {
            LoggingManager.logError(logger, errorMessage, var5);
        }

    }

    public static void selectByValue(String logger, WebElement selectElement, String selectOptionValue, String errorMessage) {
        try {
            Select select = new Select(selectElement);
            select.selectByVisibleText(selectOptionValue);
        } catch (Exception var5) {
            LoggingManager.logError(logger, errorMessage, var5);
        }

    }

    public static ExpectedCondition<Boolean> selectContains(Select select, String selectOptionText, Boolean caseSensitive) {
        return (driver) -> {
            return doesSelectContain(select, selectOptionText, caseSensitive);
        };
    }

    public static ExpectedCondition<Boolean> selectContains(WebElement selectElement, String selectOptionText, Boolean caseSensitive) {
        return (driver) -> {
            Select select = new Select(selectElement);
            return doesSelectContain(select, selectOptionText, caseSensitive);
        };
    }

    public static ExpectedCondition<Boolean> selectToBe(Select select, String selectOptionText, Boolean caseSensitive) {
        return (driver) -> {
            return doesSelectEqual(select, selectOptionText, caseSensitive);
        };
    }

    public static ExpectedCondition<Boolean> selectToBe(WebElement selectElement, String selectOptionText, Boolean caseSensitive) {
        return (driver) -> {
            Select select = new Select(selectElement);
            return doesSelectEqual(select, selectOptionText, caseSensitive);
        };
    }

    public static ExpectedCondition<Boolean> selectToBeEmpty(Select select) {
        return (driver) -> {
            return isSelectEmpty(select);
        };
    }

    public static ExpectedCondition<Boolean> selectToBeEmpty(WebElement selectElement) {
        return (driver) -> {
            Select select = new Select(selectElement);
            return isSelectEmpty(select);
        };
    }

    public static ExpectedCondition<Boolean> selectToNotBeEmpty(Select select) {
        return (driver) -> {
            return !isSelectEmpty(select);
        };
    }

    public static ExpectedCondition<Boolean> selectToNotBeEmpty(WebElement selectElement) {
        return (driver) -> {
            Select select = new Select(selectElement);
            return !isSelectEmpty(select);
        };
    }

    public static void selectUsingAngular(String logger, int selectOptionIndex, String errorMessage) {
        By selectOptionLocator = By.xpath("//span[@class='mat-option-text']");
        VisibilityUtils.waitUntilVisible(logger, selectOptionLocator, errorMessage);

        for(int i = 0; i < selectOptionIndex - 1; ++i) {
            KeyUtils.pressDown(logger);
        }

        KeyUtils.pressEnter(logger);
    }

    public static void selectUsingAngular(String logger, String selectOptionText, String errorMessage) {
        By selectOptionLocator = By.xpath("//span[@class='mat-option-text'][normalize-space()='" + selectOptionText + "']");
        VisibilityUtils.waitUntilVisible(logger, selectOptionLocator, errorMessage);
        ClickUtils.waitUntilClickable(logger, selectOptionLocator, errorMessage);
        ClickUtils.click(logger, selectOptionLocator, errorMessage);
    }

    public static void waitUntilSelectContains(String logger, Select select, String selectOptionText, Boolean caseSensitive, String errorMessage) {
        try {
            WaitUtils.waitDefault().ignoring(StaleElementReferenceException.class).until(selectContains(select, selectOptionText, caseSensitive));
        } catch (Exception var6) {
            LoggingManager.logError(logger, errorMessage, var6);
        }

    }

    public static void waitUntilSelectContains(String logger, WebElement selectElement, String selectOptionText, Boolean caseSensitive, String errorMessage) {
        try {
            WaitUtils.waitDefault().ignoring(StaleElementReferenceException.class).until(selectContains(selectElement, selectOptionText, caseSensitive));
        } catch (Exception var6) {
            LoggingManager.logError(logger, errorMessage, var6);
        }

    }

    public static void waitUntilSelectEquals(String logger, Select select, String selectOptionText, Boolean caseSensitive, String errorMessage) {
        try {
            WaitUtils.waitDefault().ignoring(StaleElementReferenceException.class).until(selectToBe(select, selectOptionText, caseSensitive));
        } catch (Exception var6) {
            LoggingManager.logError(logger, errorMessage, var6);
        }

    }

    public static void waitUntilSelectEquals(String logger, WebElement selectElement, String selectOptionText, Boolean caseSensitive, String errorMessage) {
        try {
            WaitUtils.waitDefault().ignoring(StaleElementReferenceException.class).until(selectToBe(selectElement, selectOptionText, caseSensitive));
        } catch (Exception var6) {
            LoggingManager.logError(logger, errorMessage, var6);
        }

    }

    public static void waitUntilSelectIsEmpty(String logger, Select select, String errorMessage) {
        try {
            WaitUtils.waitDefault().ignoring(StaleElementReferenceException.class).until(selectToBeEmpty(select));
        } catch (Exception var4) {
            LoggingManager.logError(logger, errorMessage, var4);
        }

    }

    public static void waitUntilSelectIsEmpty(String logger, WebElement selectElement, String errorMessage) {
        try {
            WaitUtils.waitDefault().ignoring(StaleElementReferenceException.class).until(selectToBeEmpty(selectElement));
        } catch (Exception var4) {
            LoggingManager.logError(logger, errorMessage, var4);
        }

    }

    public static void waitUntilSelectIsNotEmpty(String logger, Select select, String errorMessage) {
        try {
            WaitUtils.waitDefault().ignoring(StaleElementReferenceException.class).until(selectToNotBeEmpty(select));
        } catch (Exception var4) {
            LoggingManager.logError(logger, errorMessage, var4);
        }

    }

    public static void waitUntilSelectIsNotEmpty(String logger, WebElement selectElement, String errorMessage) {
        try {
            WaitUtils.waitDefault().ignoring(StaleElementReferenceException.class).until(selectToNotBeEmpty(selectElement));
        } catch (Exception var4) {
            LoggingManager.logError(logger, errorMessage, var4);
        }

    }

}

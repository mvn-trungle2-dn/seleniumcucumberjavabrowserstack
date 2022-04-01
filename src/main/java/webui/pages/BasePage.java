package webui.pages;

import common.Constants;
import common.utils.helpers.*;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import webui.WebDriverManager;

import java.util.List;
import java.util.Random;


public class BasePage {
    public BasePage() {
        this.initializePageElements();
    }

    public String getClassName() {
        return this.getClass().getSimpleName();
    }

    public String getPageName() {
        return this.getClass().getSimpleName();
    }

    private void initializePageElements() {
        PageFactory.initElements(WebDriverManager.getWebDriver(), this);
    }

    public void waitForPageLoaded() {
        ExpectedCondition<Boolean> condition = webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete");
        WebDriverWait wait = new WebDriverWait(WebDriverManager.getWebDriver(), 60);
        wait.until(condition);
    }

    public BasePage clearText(WebElement textElement, String errorMessage) {
        this.waitUntilVisible(textElement, errorMessage);
        TextUtils.clearText(this.getPageName(), textElement, errorMessage);
        return this;
    }

    public BasePage click(WebElement element, String errorMessage) {
        this.waitUntilVisible(element, errorMessage);
        this.waitUntilClickable(element, errorMessage);
        ClickUtils.click(this.getPageName(), element, errorMessage);
        return this;
    }

    public BasePage clickUsingJavaScript(WebElement element, String errorMessage) {
        ClickUtils.clickUsingJavaScript(this.getPageName(), element, errorMessage);
        return this;
    }

    public BasePage deselectAll(WebElement selectElement, String errorMessage) {
        this.waitUntilVisible(selectElement, errorMessage);
        SelectUtils.deselectAll(this.getPageName(), selectElement, errorMessage);
        this.waitUntilSelectIsEmpty(selectElement, errorMessage);
        return this;
    }

    public BasePage doubleClick(WebElement element, String errorMessage) {
        this.waitUntilVisible(element, errorMessage);
        this.waitUntilClickable(element, errorMessage);
        ClickUtils.doubleClick(this.getPageName(), element, errorMessage);
        return this;
    }

    public Boolean elementExists(WebElement element) {
        return VisibilityUtils.elementExists(element);
    }

    public List<WebElement> getAllSelectedOptions(WebElement selectElement, String errorMessage) {
        this.waitUntilVisible(selectElement, errorMessage);
        return SelectUtils.getAllSelectedOptions(this.getPageName(), selectElement, errorMessage);
    }

    public WebElement getFirstSelectedOption(WebElement selectElement, String errorMessage) {
        this.waitUntilVisible(selectElement, errorMessage);
        return SelectUtils.getFirstSelectedOption(this.getPageName(), selectElement, errorMessage);
    }

    public String getText(WebElement textElement, String errorMessage) {
        this.waitUntilVisible(textElement, errorMessage);
        return TextUtils.getText(this.getPageName(), textElement, errorMessage);
    }

    public String getValue(WebElement textElement, String errorMessage) {
        this.waitUntilVisible(textElement, errorMessage);
        return TextUtils.getValue(this.getPageName(), textElement, errorMessage);
    }

    public BasePage rightClick(WebElement element, String errorMessage) {
        this.waitUntilVisible(element, errorMessage);
        this.waitUntilClickable(element, errorMessage);
        ClickUtils.rightClick(this.getPageName(), element, errorMessage);
        return this;
    }

    public BasePage scrollIntoView(WebElement element, boolean alignToTop, String errorMessage) {
        ScrollingUtils.scrollIntoView(this.getPageName(), element, alignToTop, 0, errorMessage);
        return this;
    }

    public BasePage scrollIntoView(WebElement element, boolean alignToTop, int additionalPixels, String errorMessage) {
        ScrollingUtils.scrollIntoView(this.getPageName(), element, alignToTop, additionalPixels, errorMessage);
        return this;
    }

    public BasePage scrollToBottom(WebElement element, String errorMessage) {
        ScrollingUtils.scrollToBottom(this.getPageName(), element, errorMessage);
        return this;
    }

    public BasePage select(WebElement selectElement, int selectOptionIndex, String errorMessage) {
        this.waitUntilVisible(selectElement, errorMessage);
        SelectUtils.select(this.getPageName(), selectElement, selectOptionIndex, errorMessage);
        this.waitUntilSelectIsNotEmpty(selectElement, errorMessage);
        return this;
    }


    public BasePage select(WebElement selectElement, String selectOptionText, String errorMessage) {
        this.waitUntilVisible(selectElement, errorMessage);
        SelectUtils.select(this.getPageName(), selectElement, selectOptionText, errorMessage);
        this.waitUntilSelectEquals(selectElement, selectOptionText, errorMessage);
        return this;
    }


    public BasePage selectByValue(WebElement selectElement, String selectOptionValue, String errorMessage) {
        this.waitUntilVisible(selectElement, errorMessage);
        SelectUtils.selectByValue(this.getPageName(), selectElement, selectOptionValue, errorMessage);
        this.waitUntilSelectIsNotEmpty(selectElement, errorMessage);
        return this;
    }

    public BasePage selectUsingAngular(WebElement selectElement, int selectOptionIndex, String errorMessage) {
        this.click(selectElement, errorMessage);
        SelectUtils.selectUsingAngular(this.getPageName(), selectOptionIndex, errorMessage);
        this.waitUntilTextIsNotEmpty(selectElement, errorMessage);
        return this;
    }

    public BasePage selectUsingAngular(WebElement selectElement, String selectOptionText, String errorMessage) {
        this.click(selectElement, errorMessage);
        SelectUtils.selectUsingAngular(this.getPageName(), selectOptionText, errorMessage);
        return this;
    }

    public BasePage setText(WebElement textElement, String textValue, String errorMessage) {
        this.clearText(textElement, errorMessage);
        TextUtils.setText(this.getPageName(), textElement, textValue, errorMessage);
        return this;
    }

    public BasePage setText(WebElement textElement, String textValue, Boolean caseSensitive, String errorMessage) {
        this.clearText(textElement, errorMessage);
        TextUtils.setText(this.getPageName(), textElement, textValue, errorMessage);
        return this;
    }

    public BasePage setValueUsingJavaScript(WebElement textElement, String textValue, String errorMessage) {
        TextUtils.setValueUsingJavaScript(this.getPageName(), textElement, textValue, errorMessage);
        return this;
    }

    public BasePage sleep(Double timeOutInSeconds) {
        CommonUtils.sleep(this.getPageName(), timeOutInSeconds);
        return this;
    }

    public BasePage sleep(Integer timeOutInSeconds) {
        CommonUtils.sleep(this.getPageName(), timeOutInSeconds);
        return this;
    }

    public BasePage sleep(Long timeOutInSeconds) {
        CommonUtils.sleep(this.getPageName(), timeOutInSeconds);
        return this;
    }

    public WebDriverWait waitCustom(Integer timeOutInSeconds) {
        return WaitUtils.waitCustom(timeOutInSeconds);
    }

    public WebDriverWait waitCustom(Long timeOutInSeconds) {
        return WaitUtils.waitCustom(timeOutInSeconds);
    }

    public WebDriverWait waitDefault() {
        return WaitUtils.waitDefault();
    }

    public WebDriverWait waitLong() {
        return WaitUtils.waitLong();
    }

    public BasePage waitLongUntilClickable(WebElement element, String errorMessage) {
        ClickUtils.waitLongUntilClickable(this.getPageName(), element, errorMessage);
        return this;
    }

    public BasePage waitLongUntilVisible(WebElement element, String errorMessage) {
        VisibilityUtils.waitLongUntilVisible(this.getPageName(), element, errorMessage);
        return this;
    }

    public BasePage waitUntilClickable(WebElement element, String errorMessage) {
        ClickUtils.waitUntilClickable(this.getPageName(), element, errorMessage);
        return this;
    }

    public BasePage waitUntilEnabled(WebElement element, String errorMessage) {
        EnabledUtils.waitUntilEnabled(this.getPageName(), element, errorMessage);
        return this;
    }

    public BasePage waitUntilNotVisible(WebElement element, String errorMessage) {
        VisibilityUtils.waitUntilNotVisible(this.getPageName(), element, errorMessage);
        return this;
    }

    public BasePage waitUntilSelectContains(Select select, String selectOptionText, String errorMessage) {
        SelectUtils.waitUntilSelectContains(this.getPageName(), select, selectOptionText, true, errorMessage);
        return this;
    }

    public BasePage waitUntilSelectContains(WebElement selectElement, String selectOptionText, String errorMessage) {
        SelectUtils.waitUntilSelectContains(this.getPageName(), selectElement, selectOptionText, true, errorMessage);
        return this;
    }

    public BasePage waitUntilSelectContains(Select select, String selectOptionText, Boolean caseSensitive, String errorMessage) {
        SelectUtils.waitUntilSelectContains(this.getPageName(), select, selectOptionText, caseSensitive, errorMessage);
        return this;
    }

    public BasePage waitUntilSelectContains(WebElement selectElement, String selectOptionText, Boolean caseSensitive, String errorMessage) {
        SelectUtils.waitUntilSelectContains(this.getPageName(), selectElement, selectOptionText, caseSensitive, errorMessage);
        return this;
    }

    public BasePage waitUntilSelected(WebElement element, String errorMessage) {
        SelectedUtils.waitUntilSelected(this.getPageName(), element, errorMessage);
        return this;
    }

    public BasePage waitUntilSelectEquals(Select select, String selectOptionText, String errorMessage) {
        SelectUtils.waitUntilSelectEquals(this.getPageName(), select, selectOptionText, true, errorMessage);
        return this;
    }

    public BasePage waitUntilSelectEquals(WebElement selectElement, String selectOptionText, String errorMessage) {
        SelectUtils.waitUntilSelectEquals(this.getPageName(), selectElement, selectOptionText, true, errorMessage);
        return this;
    }

    public BasePage waitUntilSelectEquals(Select select, String selectOptionText, Boolean caseSensitive, String errorMessage) {
        SelectUtils.waitUntilSelectEquals(this.getPageName(), select, selectOptionText, caseSensitive, errorMessage);
        return this;
    }

    public BasePage waitUntilSelectEquals(WebElement selectElement, String selectOptionText, Boolean caseSensitive, String errorMessage) {
        SelectUtils.waitUntilSelectEquals(this.getPageName(), selectElement, selectOptionText, caseSensitive, errorMessage);
        return this;
    }

    public BasePage waitUntilSelectIsEmpty(Select select, String errorMessage) {
        SelectUtils.waitUntilSelectIsEmpty(this.getPageName(), select, errorMessage);
        return this;
    }

    public BasePage waitUntilSelectIsEmpty(WebElement selectElement, String errorMessage) {
        SelectUtils.waitUntilSelectIsEmpty(this.getPageName(), selectElement, errorMessage);
        return this;
    }

    public BasePage waitUntilSelectIsNotEmpty(Select select, String errorMessage) {
        SelectUtils.waitUntilSelectIsNotEmpty(this.getPageName(), select, errorMessage);
        return this;
    }

    public BasePage waitUntilSelectIsNotEmpty(WebElement selectElement, String errorMessage) {
        SelectUtils.waitUntilSelectIsNotEmpty(this.getPageName(), selectElement, errorMessage);
        return this;
    }

    public BasePage waitUntilTextContains(WebElement textElement, String textValue, String errorMessage) {
        TextUtils.waitUntilTextContains(this.getPageName(), textElement, textValue, errorMessage);
        return this;
    }

    public BasePage waitUntilTextIsEmpty(WebElement textElement, String errorMessage) {
        TextUtils.waitUntilTextIsEmpty(this.getPageName(), textElement, errorMessage);
        return this;
    }

    public BasePage waitUntilTextIsNotEmpty(WebElement textElement, String errorMessage) {
        TextUtils.waitUntilTextIsNotEmpty(this.getPageName(), textElement, errorMessage);
        return this;
    }

    public void waitUntilVisible(WebElement element, String errorMessage) {
        VisibilityUtils.waitUntilVisible(this.getPageName(), element, errorMessage);
    }

    public int random(int minimum, int maximum){
        Random rand = new Random();
        return minimum + rand.nextInt((maximum - minimum) + 1);
    }

    public void scrollToBottomPage(){
        ScrollingUtils.scrollToBottomPage();
    }

}

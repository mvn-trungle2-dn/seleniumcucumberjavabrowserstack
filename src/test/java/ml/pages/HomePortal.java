package ml.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import webui.pages.BasePage;

public class HomePortal extends BasePage {
    public HomePortal() {super();}

    @FindBy(className = "m-b-xs")
    public WebElement txtNewFeed;

    public void waitForPageDisplayed() {
        waitForPageLoaded();
    }

    public boolean isElementDisplayed() {
        waitLongUntilVisible(txtNewFeed, "Not found element");
       return txtNewFeed.isDisplayed();
    }
}

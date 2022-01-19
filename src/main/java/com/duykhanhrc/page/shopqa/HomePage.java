package com.duykhanhrc.page.shopqa;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.PageFactory;

/*** Import modules from Base ***/
import com.duykhanhrc.page.shopqa.common.NavigationPage;

public class HomePage extends NavigationPage {

    WebDriver driver;
	
	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void perform_Search(String search) {
		driver.navigate().to("https://shop.demoqa.com/?s=" + search + "&post_type=product");
	}
	
	public void navigateTo_HomePage() {
		driver.get("https://www.shop.demoqa.com");
	}

}

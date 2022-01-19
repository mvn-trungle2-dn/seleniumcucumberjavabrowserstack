package com.duykhanhrc.page.shopqa;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

/*** Import modules from Base ***/
import com.duykhanhrc.page.shopqa.common.NavigationPage;

public class CartPage extends NavigationPage {

    public CartPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(how = How.CSS, using = ".cart-button") 
	private WebElement btn_Cart;
	
	@FindBy(how = How.CSS, using = ".checkout-button.alt") 
	private WebElement btn_ContinueToCheckout;
	
	
	public void clickOn_Cart() {
		btn_Cart.click();
	}
	
	public void clickOn_ContinueToCheckout(){
		btn_ContinueToCheckout.click();
		try { Thread.sleep(5000);}
		catch (InterruptedException e) {}
	}

}

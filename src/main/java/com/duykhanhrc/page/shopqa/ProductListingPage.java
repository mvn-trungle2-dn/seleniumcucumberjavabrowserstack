package com.duykhanhrc.page.shopqa;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.By;

/*** Import modules from Base ***/
import com.duykhanhrc.page.shopqa.common.NavigationPage;

public class ProductListingPage extends NavigationPage {

	WebDriver driver;

    public ProductListingPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(how = How.CSS, using = "button.single_add_to_cart_button") 
	private WebElement btn_AddToCart;
	
	@FindAll(@FindBy(how = How.CSS, using = ".noo-product-inner"))
	private List<WebElement> prd_List;

	public void select_Color() {
		Select color = new Select(driver.findElement(By.cssSelector("#pa_color")));
		color.selectByVisibleText("White");
	}

	public void select_Size() {
		Select size = new Select(driver.findElement(By.cssSelector("#pa_size")));
		size.selectByVisibleText("Medium");
	}
	
	public void clickOn_AddToCart() {
		btn_AddToCart.click();
	}
	
	public void select_Product(int productNumber) {
		prd_List.get(productNumber).click();
		select_Color();
		select_Size();
	}

}

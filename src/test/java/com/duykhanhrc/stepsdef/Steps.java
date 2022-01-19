package com.duykhanhrc.stepsdef;

import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.chrome.ChromeDriver;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;

import java.io.File;

public class Steps {
	WebDriver driver;

	// @Given("^: user is on Home page$")
	// public void user_is_on_Home_page() throws Throwable {
	// 	// Write code here that turns the phrase above into concrete actions
		
	// 	String driverPath = System.getProperty("user.dir") + File.separator +
    //         "src/main/resources/WebDrivers/chromedriver.exe";
	// 	System.setProperty("webdriver.chrome.driver",driverPath);
	// 	driver = new ChromeDriver();
	// 	driver.manage().window().maximize();
	// 	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	// 	driver.get("https://shop.demoqa.com");
	// }

	// @When("^: he search for \"([^\"]*)\"$")
	// public void he_search_for(String arg1) throws Throwable {
	// 	driver.navigate().to("https://shop.demoqa.com/?s=" + arg1 + "&post_type=product");
	// }

	// @When("^: choose to buy the first item$")
	// public void choose_to_buy_the_first_item() throws Throwable {
	// 	List<WebElement> items = driver.findElements(By.cssSelector(".noo-product-inner"));
	// 	items.get(0).click();

	// 	Select color = new Select(driver.findElement(By.cssSelector("#pa_color")));
	// 	color.selectByVisibleText("White");

	// 	Select size = new Select(driver.findElement(By.cssSelector("#pa_size")));
	// 	size.selectByVisibleText("Medium");

	// 	WebElement addToCart = driver.findElement(By.cssSelector("button.single_add_to_cart_button"));
	// 	addToCart.click();		
	// }


	// @When("^: moves to checkout from mini cart$")
	// public void moves_to_checkout_from_mini_cart() throws Throwable {
	// 	WebElement cart = driver.findElement(By.cssSelector(".cart-button"));
	// 	cart.click();

	// 	WebElement continueToCheckout = driver.findElement(By.cssSelector(".checkout-button.alt"));
	// 	continueToCheckout.click();	
	// }

	// @When("^: enter persona_details on checkout page$")
	// public void enter_persona_details_on_checkout_page() throws Throwable {
	// 	Thread.sleep(5000);
	// 	WebElement firstName = driver.findElement(By.cssSelector("#billing_first_name"));
	// 	firstName.sendKeys("Lakshay");

	// 	WebElement lastName = driver.findElement(By.cssSelector("#billing_last_name"));
	// 	lastName.sendKeys("Sharma");

	// 	WebElement emailAddress = driver.findElement(By.cssSelector("#billing_email"));
	// 	emailAddress.sendKeys("test@gmail.com");

	// 	WebElement phone = driver.findElement(By.cssSelector("#billing_phone"));
	// 	phone.sendKeys("07438862327");

	// 	// WebElement countryDropDown = driver.findElement(By.cssSelector("#select2-billing_state-container"));
	// 	// countryDropDown.click();
	// 	// Thread.sleep(2000);

	// 	// List<WebElement> countryList = driver.findElements(By.cssSelector("#select2-billing_state-results li"));
	// 	// for(WebElement country : countryList){
	// 	// 	if(country.getText().equals("India")) {
	// 	// 		country.click();	
	// 	// 		Thread.sleep(3000);
	// 	// 		break;
	// 	// 	}		
	// 	// }

	// 	WebElement city = driver.findElement(By.cssSelector("#billing_city"));
	// 	city.sendKeys("Delhi");

	// 	WebElement address = driver.findElement(By.cssSelector("#billing_address_1"));
	// 	address.sendKeys("Shalimar Bagh");

	// 	WebElement postcode = driver.findElement(By.cssSelector("#billing_postcode"));
	// 	postcode.sendKeys("110088");
	
	// 	// WebElement countyDropDown = driver.findElement(By.cssSelector("#billing_state_field .select2-arrow"));
	// 	// countyDropDown.click();
	// 	// Thread.sleep(2000);
		
	// 	// List<WebElement> countyList = driver.findElements(By.cssSelector("#select2-billing_country-results li"));
	// 	// for(WebElement county : countyList){
	// 	// 	if(county.getText().equals("Delhi")) {
	// 	// 		county.click();	
	// 	// 		Thread.sleep(3000);
	// 	// 		break;
	// 	// 	}		
	// 	// }	
	// }

	// @When("^: select same delevery address$")
	// public void select_same_delevery_address() throws Throwable {
	// 	WebElement shipToDifferetAddress = driver.findElement(By.cssSelector("#ship-to-different-address-checkbox"));
	// 	shipToDifferetAddress.click();
	// 	Thread.sleep(3000);
	// }

	// @When("^: select payment method as \"([^\"]*)\" payment$")
	// public void select_payment_method_as_payment(String arg1) throws Throwable {
	// 	List<WebElement> paymentMethod = driver.findElements(By.cssSelector("ul.wc_payment_methods li"));
	// 	paymentMethod.get(0).click();
	// }

	// @When("^: place the order$")
	// public void place_the_order() throws Throwable {
	// 	WebElement acceptTC = driver.findElement(By.cssSelector("#terms.input-checkbox"));
	// 	acceptTC.click();

	// 	WebElement placeOrder = driver.findElement(By.cssSelector("#place_order"));
	// 	placeOrder.submit();
	// }


}
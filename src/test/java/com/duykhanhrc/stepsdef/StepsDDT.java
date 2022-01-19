package com.duykhanhrc.stepsdef;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;

import com.duykhanhrc.page.shopqa.CartPage;
import com.duykhanhrc.page.shopqa.CheckoutPage;
import com.duykhanhrc.page.shopqa.HomePage;
import com.duykhanhrc.page.shopqa.ProductListingPage;
import com.duykhanhrc.config.Constants;
import com.duykhanhrc.driver.LaunchBrowser;
import com.duykhanhrc.driver.DriverManager;
import com.duykhanhrc.utilities.Utils;

import java.io.File;

public class StepsDDT {

	@Given("^: user is on Home page$")
	public void user_is_on_Home_page() throws Throwable {
		// WebDriver driver = LaunchBrowser.getDriver(Constants.BROWSER_TYPE);
        // DriverManager.setDriver(driver);
		DriverManager.getDriver().get("https://shop.demoqa.com");
	}

	@When("^: he search for \"([^\"]*)\"$")
	public void he_search_for(String arg1) throws Throwable {
		HomePage home = new HomePage(DriverManager.getDriver());
		home.perform_Search("dress");
	}

	@When("^: choose to buy the first item$")
	public void choose_to_buy_the_first_item() throws Throwable {
		ProductListingPage productListingPage = new ProductListingPage(DriverManager.getDriver());
		productListingPage.select_Product(0);
		productListingPage.clickOn_AddToCart();
	}


	@When("^: moves to checkout from mini cart$")
	public void moves_to_checkout_from_mini_cart() throws Throwable {
		Utils.hardWait();
		CartPage cartPage = new CartPage(DriverManager.getDriver());
		Utils.hardWait();
		cartPage.clickOn_Cart();
		Utils.hardWait();
		cartPage.clickOn_ContinueToCheckout();	
	}

	// @When("^: enter persona_details on checkout page$")
	// public void enter_persona_details_on_checkout_page() throws Throwable {
	// 	CheckoutPage checkoutPage = new CheckoutPage(DriverManager.getDriver());
	// 	checkoutPage.fill_PersonalDetails();
	// }

	@When("^: enter \"([^\"]*)\" on checkout page$")
	public void enter_persona_details_on_checkout_page(String customer) throws Throwable {
		CheckoutPage checkoutPage = new CheckoutPage(DriverManager.getDriver());
		checkoutPage.fill_PersonalDetails2(customer);
	}

	@When("^: select same delevery address$")
	public void select_same_delevery_address() throws Throwable {
		CheckoutPage checkoutPage = new CheckoutPage(DriverManager.getDriver());
		checkoutPage.check_ShipToDifferentAddress(false);
	}

	@When("^: select payment method as \"([^\"]*)\" payment$")
	public void select_payment_method_as_payment(String arg1) throws Throwable {
		CheckoutPage checkoutPage = new CheckoutPage(DriverManager.getDriver());
		checkoutPage.select_PaymentMethod("CheckPayment");
	}

	@When("^: place the order$")
	public void place_the_order() throws Throwable {
		CheckoutPage checkoutPage = new CheckoutPage(DriverManager.getDriver());
		checkoutPage.check_TermsAndCondition(true);
		checkoutPage.clickOn_PlaceOrder();
		
		//DriverManager.quit();
	}


}
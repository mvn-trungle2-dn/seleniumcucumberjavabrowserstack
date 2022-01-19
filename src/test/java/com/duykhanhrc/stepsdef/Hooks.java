package com.duykhanhrc.stepsdef;

import org.openqa.selenium.WebDriver;
import cucumber.api.java.After;
import cucumber.api.java.Before;

import com.duykhanhrc.config.Constants;
import com.duykhanhrc.driver.LaunchBrowser;
import com.duykhanhrc.driver.DriverManager;

public class Hooks {

	public Hooks() {
	}

	@Before
	public void BeforeSteps() {
		// and here validate 'result' to decide what to do if report has failed
		/*What all you can perform here
			Starting a webdriver
			Setting up DB connections
			Setting up test data
			Setting up browser cookies
			Navigating to certain page
			or anything before the test
		*/
        WebDriver driver = LaunchBrowser.getDriver(Constants.BROWSER_TYPE);
        DriverManager.setDriver(driver);
	}

	@After
	public void AfterSteps() {
		DriverManager.quit();

	}

}
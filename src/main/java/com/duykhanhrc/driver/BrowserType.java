package com.duykhanhrc.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.CapabilityType;

import java.io.File;
import java.net.URL;

import com.duykhanhrc.config.Constants;

public enum BrowserType { 
    CHROME("Chrome") {
        @Override
        public WebDriver createDriver() {
            // Chromedriver path
            driverPath = System.getProperty("user.dir") + File.separator +
            "src/main/resources/WebDrivers/chromedriver.exe";
            ChromeOptions options = new ChromeOptions();
            System.setProperty("webdriver.chrome.driver", driverPath);
            driver = new ChromeDriver(options);
            // Maximize the browser
            driver.manage().window().maximize();

            return driver;
        }

        @Override
        public WebDriver createRemoteDriver() {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            ChromeOptions options = new ChromeOptions();
            
            capabilities.setCapability(CapabilityType.BROWSER_NAME, "chrome");
            capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
            capabilities.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
            capabilities.setCapability(ChromeOptions.CAPABILITY, options);

            driver = rWebDriver(capabilities);

            return driver;
        }
    },
    EDGE("Edge") {
        @Override
        public WebDriver createDriver() {
            driverPath = "src/main/resources/WebDrivers/msedgedriver.exe";
            EdgeOptions options = new EdgeOptions();
            System.setProperty("webdriver.edge.driver", driverPath);
            driver = new EdgeDriver(options);
            // Maximize the browser
            driver.manage().window().maximize();

            return driver;
        }

        @Override
        public WebDriver createRemoteDriver() {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            EdgeOptions options = new EdgeOptions();
            
            capabilities.setCapability(CapabilityType.BROWSER_NAME, "MicrosoftEdge");
            capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
            capabilities.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
            capabilities.setCapability(ChromeOptions.CAPABILITY, options);

            driver = rWebDriver(capabilities);

            return driver;
        }
    };

    private final String deviceName;

    BrowserType(String deviceName) {
        this.deviceName = deviceName;
    }

    private static WebDriver rWebDriver(DesiredCapabilities capabilities) {
        try {
            driver = new RemoteWebDriver(new URL(gridURL), capabilities);
            driver.manage().window().maximize();
        } catch (Exception e) {
            System.out.println("Driver error!");
            System.out.println(e.getMessage());
        }

        return driver;
    }

    static WebDriver driver;
    static private String driverPath;
    static String gridURL = String.format("http://%s/wd/hub", Constants.Grid_Url);

    public abstract WebDriver createDriver();

    public abstract WebDriver createRemoteDriver();
}
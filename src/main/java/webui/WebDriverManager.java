package webui;

import common.Constants;
import common.LoggingManager;
import common.utils.PropertyUtils;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.SessionId;

import java.net.URL;
import java.util.HashMap;

public class WebDriverManager {
    private static final String logger = WebDriverManager.class.getSimpleName();
    private static ThreadLocal<WebDriver> webDrivers = new ThreadLocal();

    public WebDriverManager() {
    }

    public static synchronized WebDriver getWebDriver() {
        return webDrivers.get();
    }

    public static synchronized void setWebDriver(WebDriver webDriver) {
        webDrivers.set(webDriver);
    }

    public static synchronized Boolean doesDriverExist() {
        return getWebDriver() != null;
    }

    public static synchronized Boolean isDriverSessionActive() {
        return getWebDriverSessionId() != null;
    }

    private static synchronized SessionId getWebDriverSessionId() {
        SessionId sessionId = null;
        if (doesDriverExist()) {
            if (PropertyUtils.getSeleniumGrid().equalsIgnoreCase("true")) {
                sessionId = ((RemoteWebDriver) getWebDriver()).getSessionId();
            } else if (PropertyUtils.isBrowser("Chrome")) {
                sessionId = ((ChromeDriver) getWebDriver()).getSessionId();
            } else if (PropertyUtils.isBrowser("Edge")) {
                sessionId = ((EdgeDriver) getWebDriver()).getSessionId();
            } else if (PropertyUtils.isBrowser("Firefox")) {
                sessionId = ((FirefoxDriver) getWebDriver()).getSessionId();
            }
        }
        return sessionId;
    }

    public static WebDriver openNewBrowser() {
        WebDriver webDriver = null;
        if (PropertyUtils.isBrowser("Chrome")) {
            webDriver = openNewChromeBrowser();
        }
        if (PropertyUtils.isBrowser("Edge")) {
            webDriver = openNewEdgeBrowser();
        }
        if (PropertyUtils.isBrowser("Firefox")) {
            webDriver = openNewFirefoxBrowser();
        }
        return webDriver;
    }

    public static WebDriver openNewEdgeBrowser() {
        LoggingManager.logDebug(logger, "browser = Edge");
        io.github.bonigarcia.wdm.WebDriverManager.edgedriver().setup();
        EdgeOptions edgeOptions = new EdgeOptions();
        HashMap<String, Object> edgePrefs = new HashMap();

        edgeOptions.setExperimentalOption("prefs", edgePrefs);
        edgeOptions.setAcceptInsecureCerts(true);
        edgeOptions.addArguments("-InPrivate");
        edgeOptions.addArguments("--log-level=3");
        edgeOptions.addArguments("--disable-logging");
        edgeOptions.addArguments("--window-size", PropertyUtils.getResolution());
        edgeOptions.addArguments("--no-sandbox");
        edgeOptions.addArguments("--disable-gpu");
        edgeOptions.addArguments("--allow-running-insecure-content");
        edgeOptions.addArguments("--ignore-certificate-errors=yes");
        edgeOptions.addArguments("--ignore-ssl-errors=yes");
        LoggingManager.logDebug(logger, "browser.headless = true");

        Object edgeDriver;
        if (PropertyUtils.getBrowserHeadless().equalsIgnoreCase("true")) {
            edgeOptions.setHeadless(true);
        }
        if (PropertyUtils.getSeleniumGrid().equalsIgnoreCase("true")) {
            edgeDriver = connectToSeleniumGrid(edgeOptions);
        } else {
            edgeDriver = new EdgeDriver(edgeOptions);
        }
        setWebDriver((WebDriver) edgeDriver);
        return (WebDriver) edgeDriver;
    }

    public static WebDriver openNewChromeBrowser() {
        LoggingManager.logDebug(logger, "browser = Chrome");
        io.github.bonigarcia.wdm.WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setAcceptInsecureCerts(true);
        HashMap<String, Object> chromePreferences = new HashMap();
        chromePreferences.put("download.prompt_for_download", false);
        chromeOptions.setExperimentalOption("prefs", chromePreferences);
        if (PropertyUtils.getBrowserHeadless().equalsIgnoreCase("true")) {
            LoggingManager.logDebug(logger, "browser.headless = true");
            chromeOptions.addArguments(new String[]{"--disable-dev-shm-usage"});
            chromeOptions.addArguments(new String[]{"--disable-gpu"});
            chromeOptions.addArguments(new String[]{"--no-sandbox"});
            chromeOptions.addArguments(new String[]{"--window-size=" + PropertyUtils.getResolution()});
            chromeOptions.setHeadless(true);
        }
        if (PropertyUtils.getBrowserPrivate().equalsIgnoreCase("true")) {
            LoggingManager.logDebug(logger, "browser.private = true");
            chromeOptions.addArguments(new String[]{"--incognito"});
        }
        Object chromeDriver;
        if (PropertyUtils.getSeleniumGrid().equalsIgnoreCase("true")) {
            chromeDriver = connectToSeleniumGrid(chromeOptions);
        } else {
            chromeDriver = new ChromeDriver(chromeOptions);
        }
        setWebDriver((WebDriver) chromeDriver);
        return (WebDriver) chromeDriver;
    }

    public static WebDriver openNewFirefoxBrowser() {
        LoggingManager.logDebug(logger, "browser = Firefox");
        io.github.bonigarcia.wdm.WebDriverManager.firefoxdriver().setup();
        FirefoxProfile firefoxProfile = new FirefoxProfile();
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.setAcceptInsecureCerts(true);
        firefoxOptions.setProfile(firefoxProfile);
        if (PropertyUtils.getBrowserHeadless().equalsIgnoreCase("true")) {
            LoggingManager.logDebug(logger, "browser.headless = true");
            firefoxOptions.setHeadless(true);
        }
        if (PropertyUtils.getBrowserPrivate().equalsIgnoreCase("true")) {
            LoggingManager.logDebug(logger, "browser.private = true");
            firefoxOptions.addPreference("browser.privatebrowsing.autostart", true);
        }
        Object firefoxDriver;
        if (PropertyUtils.getSeleniumGrid().equalsIgnoreCase("true")) {
            firefoxDriver = connectToSeleniumGrid(firefoxOptions);
        } else {
            firefoxDriver = new FirefoxDriver(firefoxOptions);
        }
        setWebDriver((WebDriver) firefoxDriver);
        return (WebDriver) firefoxDriver;
    }

    private static RemoteWebDriver connectToSeleniumGrid(Capabilities browserOptions) {
        LoggingManager.logDebug(logger, "seleniumGrid = true");
        String seleniumGridHub = PropertyUtils.getSeleniumGridHub();
        try {
            LoggingManager.logDebug(logger, "seleniumGrid.hub = " + seleniumGridHub);
            RemoteWebDriver remoteWebDriver = new RemoteWebDriver(new URL(seleniumGridHub), browserOptions);
            remoteWebDriver.setFileDetector(new LocalFileDetector());
            return remoteWebDriver;
        } catch (Exception var4) {
            throw new RuntimeException("Unable to create a new RemoteWebDriver to '" + seleniumGridHub + "'");
        }
    }

    public static void maximizeWindow() {
        WebDriver webDriver = getWebDriver();
        if (webDriver != null) {
            webDriver.manage().window().maximize();
        }
    }

    public static void loadUrl(String url) {
        WebDriver webDriver = getWebDriver();
        if (webDriver != null) {
            webDriver.get(url);
        }
    }

    public static String getTitle() {
        WebDriver webDriver = getWebDriver();
        if (webDriver != null) {
            return webDriver.getTitle();
        }
        return null;
    }

    public static void closeWindow() {
        WebDriver webDriver = getWebDriver();
        if (webDriver != null) {
            webDriver.close();
        }
    }

    public static void closeBrowser() {
        WebDriver webDriver = getWebDriver();
        if (webDriver != null) {
            webDriver.quit();
        }
    }
}
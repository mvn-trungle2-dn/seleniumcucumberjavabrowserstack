package common.utils;

import common.LoggingManager;
import common.utils.file.FileUtils;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

public class PropertyUtils {
    private static final String CLASS_NAME = PropertyUtils.class.getSimpleName();
    private static final Properties PROPERTIES_FILES = loadPropertiesFiles(getPropertiesFiles());

    public PropertyUtils() {
    }

    public static Properties loadPropertiesFiles(String propertiesFiles) {
        Properties properties = new Properties();
        String userDir = getUserDirectory();
        InputStream inputStream = null;
        String[] allPropertiesFiles = propertiesFiles.split(",");
        String[] var5 = allPropertiesFiles;
        int var6 = allPropertiesFiles.length;

        for (int var7 = 0; var7 < var6; ++var7) {
            String propertiesFileName = var5[var7];
            propertiesFileName = propertiesFileName.trim();

            try {
//                inputStream = new FileInputStream(userDir + "/src/test/resources/" + propertiesFileName);
                inputStream = new FileInputStream(userDir + File.separator + propertiesFileName);

                if (propertiesFileName.endsWith(".yaml")) {
                    Yaml yaml = new Yaml();
                    Map map = (Map) yaml.load(inputStream);
                    Iterator var11 = map.keySet().iterator();

                    while (var11.hasNext()) {
                        Object key = var11.next();
                        properties.setProperty(key.toString(), map.get(key).toString());
                    }
                } else if (propertiesFileName.endsWith(".xml")) {
                    properties.loadFromXML(inputStream);
                } else {
                    properties.load(inputStream);
                }
            } catch (Exception var16) {
                LoggingManager.logTrace(CLASS_NAME, "Unable to load properties file '" + propertiesFileName + "'");
            } finally {
                FileUtils.closeFileStream(inputStream);
            }
        }

        return properties;
    }

    public static String getProperty(String propertyKey) {
        String propertyValueFromFile = null;
        try {
            propertyValueFromFile = PROPERTIES_FILES.getProperty(propertyKey);
        } catch (Exception var3) {
            LoggingManager.logTrace(CLASS_NAME, "Property '" + propertyKey + "' does not exist in any of the properties files: '" + getPropertiesFiles() + "'");
        }
        String propertyValueFromSystem = System.getProperty(propertyKey, propertyValueFromFile);
        LoggingManager.logTrace(CLASS_NAME, propertyKey + " = " + propertyValueFromSystem);
        return propertyValueFromSystem;
    }

    public static String getProperty(String propertyKey, String defaultValue) {
        String propertyValueFromFile = defaultValue;
        try {
            propertyValueFromFile = PROPERTIES_FILES.getProperty(propertyKey, defaultValue);
        } catch (Exception var4) {
            LoggingManager.logTrace(CLASS_NAME, "Property '" + propertyKey + "' does not exist in any of the properties files: '" + getPropertiesFiles() + "'");
        }
        String propertyValueFromSystem = System.getProperty(propertyKey, propertyValueFromFile);
        LoggingManager.logTrace(CLASS_NAME, propertyKey + " = " + propertyValueFromSystem);
        return propertyValueFromSystem;
    }

    public static String getBrowser() {
        return getProperty("browser", "none");
    }

    public static Boolean isBrowser(String browserExpected) {
        return getBrowser().equalsIgnoreCase(browserExpected);
    }

    public static Boolean isBrowserValid() {
        return isBrowser("Chrome") || isBrowser("Edge") || isBrowser("Firefox");
    }

    public static String getBrowserHeadless() {
        return getProperty("browser.headless", "false");
    }

    public static String getBrowserPrivate() {
        return getProperty("browser.private", "false");
    }

    public static String getEnvironment() {
        return getProperty("env", "Dev");
    }

    public static String getResolution() {
        return getProperty("resolution", "1920x1080");
    }

    public Boolean isEnvironment(String expectedEnvironment) {
        return getEnvironment().equalsIgnoreCase(expectedEnvironment);
    }

    public static String getEnvironmentUrlDefault() {
        return getProperty("url");
    }

    public static String getLoginPassword() {
        return getProperty("login.password");
    }

    public static String getLoginUsername() {
        return getProperty("login.username");
    }

    public static String getOperatingSystem() {
        return System.getProperty("os.name");
    }

    public static Boolean isOperatingSystem(String operatingSystemExpected) {
        return getOperatingSystem().toUpperCase().startsWith(operatingSystemExpected.toUpperCase());
    }

    public static String getOutputDirectory() {
        return getProperty("output.dir", "test-output");
    }

    public static String getPassword() {
        return getProperty("password");
    }

    public static String getPropertiesFile() {
        return System.getProperty("propertiesFile", "dev.properties");
    }

    public static String getPropertiesFiles() {
        return System.getProperty("propertiesFiles", getPropertiesFile());
    }

    public static String getReportsDirectory() {
        return getProperty("reports.dir", "extent-reports");
    }

    public static String getScreenshotsDirectory() {
        return getProperty("screenshots.dir", "screenshots");
    }

    public static String getSeleniumGrid() {
        return getProperty("seleniumGrid", "false");
    }

    public static String getSeleniumGridHub() {
        return getProperty("seleniumGrid.hub");
    }

    public static String getSkipTags() {
        return getProperty("skipTags", "@IGNORE, @PENDING, @SKIP");
    }

    public static String getUserDirectory() {
        return System.getProperty("user.dir");
    }

    public static double getWaitInterval() {
        String waitTime = getProperty("wait.interval", "0.5");
        return Double.parseDouble(waitTime);
    }

    public static long getWaitTime() {
        String waitTime = getProperty("wait", "10");
        return Long.parseLong(waitTime);
    }

    public static long getWaitTimeLong() {
        String waitTimeLong = getProperty("wait.long", "60");
        return Long.parseLong(waitTimeLong);
    }
}

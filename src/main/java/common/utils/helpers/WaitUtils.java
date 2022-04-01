package common.utils.helpers;

import common.utils.PropertyUtils;
import org.openqa.selenium.support.ui.WebDriverWait;
import webui.WebDriverManager;

public class WaitUtils {
    private static double WAIT_INTERVAL = PropertyUtils.getWaitInterval();
    private static long WAIT_INTERVAL_MS;
    private static long WAIT_TIME;
    private static long WAIT_TIME_LONG;

    public WaitUtils() {
    }

    public static WebDriverWait waitCustom(Integer timeOutInSeconds) {
        return new WebDriverWait(WebDriverManager.getWebDriver(), (long)timeOutInSeconds, WAIT_INTERVAL_MS);
    }

    public static WebDriverWait waitCustom(Long timeOutInSeconds) {
        return new WebDriverWait(WebDriverManager.getWebDriver(), timeOutInSeconds, WAIT_INTERVAL_MS);
    }

    public static WebDriverWait waitDefault() {
        return new WebDriverWait(WebDriverManager.getWebDriver(), WAIT_TIME, WAIT_INTERVAL_MS);
    }

    public static WebDriverWait waitLong() {
        return new WebDriverWait(WebDriverManager.getWebDriver(), WAIT_TIME_LONG, WAIT_INTERVAL_MS);
    }

    static {
        WAIT_INTERVAL_MS = (long)(WAIT_INTERVAL * 1000.0D);
        WAIT_TIME = PropertyUtils.getWaitTime();
        WAIT_TIME_LONG = PropertyUtils.getWaitTimeLong();
    }
}
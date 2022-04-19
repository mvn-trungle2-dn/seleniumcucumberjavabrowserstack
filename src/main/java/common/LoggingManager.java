package common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

public class LoggingManager {

    public LoggingManager() {
    }

    private static Logger getLogger(String className) {
        return LoggerFactory.getLogger(className);
    }

    public static void logTrace(String className, String message) {
        getLogger(className).trace(message);
    }

    public static void logDebug(String className, String message) {
        getLogger(className).debug(message);
    }

    public static void logInfo(String className, String message) {
        getLogger(className).info(message);
    }

    public static void logPass(String className, String message) {
        getLogger(className).info(message);
    }

    public static void logError(String className, String message) {
        getLogger(className).error(message);
        Assert.fail(message);
    }

    public static void logError(String className, String message, Exception exception) {
        getLogger(className).error(message, exception);
        Assert.fail(message, exception);
    }

    public static void logFail(String className, String message) {
        getLogger(className).error(message);
        Assert.fail(message);
    }

    public static void assertTrue(String className, Boolean condition, String passMessage, String failMessage) {
        if (condition) {
            logPass(className, passMessage);
        } else {
            logFail(className, failMessage);
        }

    }
}


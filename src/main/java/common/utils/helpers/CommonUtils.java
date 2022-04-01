package common.utils.helpers;

import common.LoggingManager;

public class CommonUtils {
    public CommonUtils() {
    }

    public static void sleep(String className, Double timeOutInSeconds) {
        try {
            Thread.sleep((long)(timeOutInSeconds * 1000.0D));
        } catch (InterruptedException var3) {
            LoggingManager.logError(className, "Thread.sleep was interrupted", var3);
        }

    }

    public static void sleep(String className, Integer timeOutInSeconds) {
        try {
            Thread.sleep((long)(timeOutInSeconds * 1000));
        } catch (InterruptedException var3) {
            LoggingManager.logError(className, "Thread.sleep was interrupted", var3);
        }
    }

    public static void sleep(int timeOutInSeconds) {
        try {
            Thread.sleep(timeOutInSeconds * 1000L);
        } catch (InterruptedException var3) {
            var3.getStackTrace();
        }
    }

    public static void sleep(String className, Long timeOutInSeconds) {
        try {
            Thread.sleep(timeOutInSeconds * 1000L);
        } catch (InterruptedException var3) {
            LoggingManager.logError(className, "Thread.sleep was interrupted", var3);
        }

    }
}
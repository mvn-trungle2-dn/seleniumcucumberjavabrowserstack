package com.duykhanhrc.utilities;

public class Utils {
    
    public static void hardWait(int timeout) {
        try {
            Thread.sleep(timeout);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void hardWait(){
        hardWait(3000);
    }

}
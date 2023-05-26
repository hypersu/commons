package com.hs.commons.lang;

public class SleepUtil {

    public static void sleep() {
        try {
            Thread.sleep(5000);
        } catch (Exception e) {
            // ignore
        }
    }

}

package com.hs.commons.lang;

public class CloseUtil {

    public static void close(AutoCloseable closeable) {
        try {
            if (closeable != null) {
                closeable.close();
            }
        } catch (Exception e) {
            // ignore
        }
    }

}

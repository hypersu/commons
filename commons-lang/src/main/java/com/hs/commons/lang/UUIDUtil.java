package com.hs.commons.lang;

import java.util.UUID;

public class UUIDUtil {
    public static String getId() {
        return UUID.randomUUID().toString();
    }

    public static String getId32() {
        return UUID.randomUUID().toString().replaceAll(StringPool.DASH, StringPool.EMPTY);
    }
}

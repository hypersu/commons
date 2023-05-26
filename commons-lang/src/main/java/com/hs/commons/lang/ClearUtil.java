package com.hs.commons.lang;

import com.hs.commons.lang.Clearable;

public class ClearUtil {

    public static void clear(Clearable clearable) {
        try {
            if (clearable != null) {
                clearable.clear();
            }
        } catch (Exception e) {
        }
    }

}

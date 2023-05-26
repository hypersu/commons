package com.hs.commons.lang;

public class NumberUtil {
    public static int divideCeiling(int x, int y) {
        int z = x % y;
        if (z > 0) {
            return x / y + 1;
        }
        return x / y;
    }

    /**
     * 小端序
     */
    public static byte[] toByteLittle(int n) {
        byte[] b = new byte[4];
        b[0] = (byte) (n & 0xff);
        b[1] = (byte) (n >> 8 & 0xff);
        b[2] = (byte) (n >> 16 & 0xff);
        b[3] = (byte) (n >> 24 & 0xff);
        return b;
    }

    public static byte[] toByte3Little(int n) {
        byte[] b = new byte[3];
        b[0] = (byte) (n & 0xff);
        b[1] = (byte) (n >> 8 & 0xff);
        b[2] = (byte) (n >> 16 & 0xff);
        return b;
    }

    public static byte[] toByteLittle(short n) {
        byte[] b = new byte[2];
        b[0] = (byte) (n & 0xff);
        b[1] = (byte) (n >> 8 & 0xff);
        return b;
    }

    /**
     * 小端序
     */
    public static int toIntLittle(byte[] bytes) {
        int int1 = bytes[0] & 0xff;
        int int2 = (bytes[1] & 0xff) << 8;
        int int3 = (bytes[2] & 0xff) << 16;
        int int4 = (bytes[3] & 0xff) << 24;
        return int1 | int2 | int3 | int4;
    }

    public static int toInt3Little(byte[] bytes) {
        int int1 = bytes[0] & 0xff;
        int int2 = (bytes[1] & 0xff) << 8;
        int int3 = (bytes[2] & 0xff) << 16;
        return int1 | int2 | int3;
    }

    public static short toShortLittle(byte[] bytes) {
        short short1 = (short) (bytes[0] & 0xff);
        short short2 = (short) ((bytes[1] & 0xff) << 8);
        return (short) (short1 | short2);
    }


    /**
     * 大端序
     */
    public static byte[] toByteBig(int i) {
        byte[] bytes = new byte[4];
        bytes[3] = (byte) (i & 0xff);
        bytes[2] = (byte) (i >> 8 & 0xff);
        bytes[1] = (byte) (i >> 16 & 0xff);
        bytes[0] = (byte) (i >> 24 & 0xff);
        return bytes;
    }

    /**
     * 大端序
     */
    public static int toIntBig(byte[] bytes) {
        int int1 = bytes[3] & 0xff;
        int int2 = (bytes[2] & 0xff) << 8;
        int int3 = (bytes[1] & 0xff) << 16;
        int int4 = (bytes[0] & 0xff) << 24;
        return int1 | int2 | int3 | int4;
    }

    public static byte[] toByteBig(long n) {
        byte[] b = new byte[8];
        b[7] = (byte) (n & 0xff);
        b[6] = (byte) (n >> 8 & 0xff);
        b[5] = (byte) (n >> 16 & 0xff);
        b[4] = (byte) (n >> 24 & 0xff);
        b[3] = (byte) (n >> 32 & 0xff);
        b[2] = (byte) (n >> 40 & 0xff);
        b[1] = (byte) (n >> 48 & 0xff);
        b[0] = (byte) (n >> 56 & 0xff);
        return b;
    }

    public static byte[] toByteLittle(long n) {
        byte[] b = new byte[8];
        b[0] = (byte) (n & 0xff);
        b[1] = (byte) (n >> 8 & 0xff);
        b[2] = (byte) (n >> 16 & 0xff);
        b[3] = (byte) (n >> 24 & 0xff);
        b[4] = (byte) (n >> 32 & 0xff);
        b[5] = (byte) (n >> 40 & 0xff);
        b[6] = (byte) (n >> 48 & 0xff);
        b[7] = (byte) (n >> 56 & 0xff);
        return b;
    }

    public static long toLongLittle(byte[] bytes) {
        return ((((long) bytes[0] & 0xff) << 0)
                | (((long) bytes[1] & 0xff) << 8)
                | (((long) bytes[2] & 0xff) << 16)
                | (((long) bytes[3] & 0xff) << 24)
                | (((long) bytes[4] & 0xff) << 32)
                | (((long) bytes[5] & 0xff) << 40)
                | (((long) bytes[6] & 0xff) << 48)
                | (((long) bytes[7] & 0xff) << 56));
    }

    public static long toLongBig(byte[] bytes) {
        return ((((long) bytes[0] & 0xff) << 56)
                | (((long) bytes[1] & 0xff) << 48)
                | (((long) bytes[2] & 0xff) << 40)
                | (((long) bytes[3] & 0xff) << 32)
                | (((long) bytes[4] & 0xff) << 24)
                | (((long) bytes[5] & 0xff) << 16)
                | (((long) bytes[6] & 0xff) << 8)
                | (((long) bytes[7] & 0xff) << 0));
    }

    public static int toInt(final String str, final int defaultValue) {
        if (str == null) {
            return defaultValue;
        }
        try {
            return Integer.parseInt(str);
        } catch (final NumberFormatException nfe) {
            return defaultValue;
        }
    }

    public static int toInt(final String str) {
        return toInt(str, 0);
    }
}

package com.hs.commons.lang;

import java.text.DecimalFormat;

public class FileUtil {
    public static final String FILE_SYSTEM_LINUX = "linux";
    public static final String FILE_SYSTEM_WINDOWS = "windows";
    public static final char SEPARATOR_CHAR_LINUX = '/';
    public static final char SEPARATOR_CHAR_WINDOWS = '\\';

    /**
     * 判断文件夹最后一个字符
     */
    public static String getFileSystem(String path) {
        if (path == null || path.contains(SEPARATOR_CHAR_WINDOWS + "")) {
            return FILE_SYSTEM_WINDOWS;
        }
        return FILE_SYSTEM_LINUX;
    }

    public static String getParent(String path, String fileSystem) {
        char defaultSeparatorChar = getSeparatorChar(fileSystem);
        int index = path.lastIndexOf(defaultSeparatorChar);
        int prefixLength = prefixLength(path, defaultSeparatorChar);
        if (index < prefixLength) {
            if ((prefixLength > 0) && (path.length() > prefixLength))
                return path.substring(0, prefixLength);
            return null;
        }
        return path.substring(0, index);
    }

    public static String getName(String path, String fileSystem) {
        char defaultSeparatorChar = getSeparatorChar(fileSystem);
        int index = path.lastIndexOf(defaultSeparatorChar);
        int prefixLength = prefixLength(path, defaultSeparatorChar);
        if (index < prefixLength) {
            return path.substring(prefixLength);
        }
        return path.substring(index + 1);
    }

    public static String getName(String path) {
        String fileSystem = getFileSystem(path);
        return getName(path, fileSystem);
    }

    public static String replacePath(String srcPath, String destPath, boolean containedFileName) {
        String srcFileSystem = getFileSystem(srcPath);
        char srcSeparatorChar = getSeparatorChar(srcFileSystem);
        if (!containedFileName
                && !srcPath.endsWith(srcSeparatorChar + "")
                && (srcPath = getParent(srcPath, srcFileSystem)) != null) {
            srcPath = srcPath + srcSeparatorChar;
        }
        if (srcPath == null) {
            return destPath;
        }
        if (srcPath.startsWith(srcSeparatorChar + "")) {
            srcPath = srcPath.substring(1);
        }
        String destFileSystem = getFileSystem(destPath);
        char destSeparatorChar = getSeparatorChar(destFileSystem);
        srcPath = srcPath.replace(srcSeparatorChar, destSeparatorChar);
        return destPath + srcPath;
    }

    public static String replacePath(String srcPath, String srcParentPath, String destPath, boolean containedFileName) {
        if (srcPath == null
                || srcParentPath == null
                || destPath == null) {
            return null;
        }
        String prefixPath = srcPath.replaceFirst(srcParentPath, "");
        return replacePath(prefixPath, destPath, containedFileName);
    }

    public static String replacePath(String srcPath, String srcParentPath, String destPath) {
        return replacePath(srcPath, srcParentPath, destPath, true);
    }

    public static char getSeparatorChar(String fileSystem) {
        switch (fileSystem) {
            case FILE_SYSTEM_LINUX:
                return SEPARATOR_CHAR_LINUX;
            default:
                return SEPARATOR_CHAR_WINDOWS;
        }
    }

    public static int prefixLength(String path, char defaultSeparatorChar) {
        char slash = defaultSeparatorChar;
        int n = path.length();
        if (n == 0) return 0;
        char c0 = path.charAt(0);
        char c1 = (n > 1) ? path.charAt(1) : 0;
        if (c0 == slash) {
            if (c1 == slash) return 2;  /* Absolute UNC pathname "\\\\foo" */
            return 1;                   /* Drive-relative "\\foo" */
        }
        if (isLetter(c0) && (c1 == ':')) {
            if ((n > 2) && (path.charAt(2) == slash))
                return 3;               /* Absolute local pathname "z:\\foo" */
            return 2;                   /* Directory-relative "z:foo" */
        }
        return 0;                       /* Completely relative */
    }

    public static boolean isLetter(char c) {
        return ((c >= 'a') && (c <= 'z')) || ((c >= 'A') && (c <= 'Z'));
    }

    /**
     * 计算文件大小
     */
    public static String formatLength(long length) {
        // 转换文件大小
        DecimalFormat df = new DecimalFormat("#.00");
        String fileSizeString = "";
        if (length < 1024) {
            fileSizeString = df.format((double) length) + "B";
        } else if (length < 1048576) {
            fileSizeString = df.format((double) length / 1024) + "K";
        } else if (length < 1073741824) {
            fileSizeString = df.format((double) length / 1048576) + "M";
        } else {
            fileSizeString = df.format((double) length / 1073741824) + "G";
        }
        return fileSizeString;
    }

}

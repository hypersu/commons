package com.hs.commons.lang;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class IPUtil {
    private static final Pattern IPV4 = Pattern.compile("^(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)(\\.(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)){3}$");
    private static final Pattern IPV4_MASK = Pattern.compile("^((25[0-5]|2[0-4]\\d|[01]?\\d\\d?)\\.){3}(25[0-5]|2[0-4]\\d|[01]?\\d\\d?)(\\/(\\d|[1-2]\\d|3[0-2]))?$");
    private static final Pattern IPV6_MASK = Pattern.compile("^([\\da-fA-F]{1,4}:){6}((25[0-5]|2[0-4]\\d|[01]?\\d\\d?)\\.){3}(25[0-5]|2[0-4]\\d|[01]?\\d\\d?)(\\/([1-9]?\\d|(1([0-1]\\d|2[0-8]))))?$|^::([\\da-fA-F]{1,4}:){0,4}((25[0-5]|2[0-4]\\d|[01]?\\d\\d?)\\.){3}(25[0-5]|2[0-4]\\d|[01]?\\d\\d?)(\\/([1-9]?\\d|(1([0-1]\\d|2[0-8]))))?$|^([\\da-fA-F]{1,4}:):([\\da-fA-F]{1,4}:){0,3}((25[0-5]|2[0-4]\\d|[01]?\\d\\d?)\\.){3}(25[0-5]|2[0-4]\\d|[01]?\\d\\d?)(\\/([1-9]?\\d|(1([0-1]\\d|2[0-8]))))?$|^([\\da-fA-F]{1,4}:){2}:([\\da-fA-F]{1,4}:){0,2}((25[0-5]|2[0-4]\\d|[01]?\\d\\d?)\\.){3}(25[0-5]|2[0-4]\\d|[01]?\\d\\d?)(\\/([1-9]?\\d|(1([0-1]\\d|2[0-8]))))?$|^([\\da-fA-F]{1,4}:){3}:([\\da-fA-F]{1,4}:){0,1}((25[0-5]|2[0-4]\\d|[01]?\\d\\d?)\\.){3}(25[0-5]|2[0-4]\\d|[01]?\\d\\d?)(\\/([1-9]?\\d|(1([0-1]\\d|2[0-8]))))?$|^([\\da-fA-F]{1,4}:){4}:((25[0-5]|2[0-4]\\d|[01]?\\d\\d?)\\.){3}(25[0-5]|2[0-4]\\d|[01]?\\d\\d?)(\\/([1-9]?\\d|(1([0-1]\\d|2[0-8]))))?$|^([\\da-fA-F]{1,4}:){7}[\\da-fA-F]{1,4}(\\/([1-9]?\\d|(1([0-1]\\d|2[0-8]))))?$|^:((:[\\da-fA-F]{1,4}){1,6}|:)(\\/([1-9]?\\d|(1([0-1]\\d|2[0-8]))))?$|^[\\da-fA-F]{1,4}:((:[\\da-fA-F]{1,4}){1,5}|:)(\\/([1-9]?\\d|(1([0-1]\\d|2[0-8]))))?$|^([\\da-fA-F]{1,4}:){2}((:[\\da-fA-F]{1,4}){1,4}|:)(\\/([1-9]?\\d|(1([0-1]\\d|2[0-8]))))?$|^([\\da-fA-F]{1,4}:){3}((:[\\da-fA-F]{1,4}){1,3}|:)(\\/([1-9]?\\d|(1([0-1]\\d|2[0-8]))))?$|^([\\da-fA-F]{1,4}:){4}((:[\\da-fA-F]{1,4}){1,2}|:)(\\/([1-9]?\\d|(1([0-1]\\d|2[0-8]))))?$|^([\\da-fA-F]{1,4}:){5}:([\\da-fA-F]{1,4})?(\\/([1-9]?\\d|(1([0-1]\\d|2[0-8]))))?$|^([\\da-fA-F]{1,4}:){6}:(\\/([1-9]?\\d|(1([0-1]\\d|2[0-8]))))?$");
    // 无全0块，标准IPv6地址的正则表达式
    private static final Pattern IPV6_STD = Pattern.compile("^([0-9a-fA-F]{1,4}:){7}[0-9a-fA-F]{1,4}$");
    // 压缩正则表达式
    private static final Pattern IPV6_COMPRESS = Pattern.compile("^(([0-9A-Fa-f]{1,4}(:[0-9A-Fa-f]{1,4})*)?)::((([0-9A-Fa-f]{1,4}:)*[0-9A-Fa-f]{1,4})?)$");

    // 判断是否为合法IPv4地址
    public static boolean isIPv4(final String address) {
        return IPV4.matcher(address).matches();
    }

    // 判断是否为合法IPv6地址
    public static boolean isIPv6(String address) {
        boolean bool = false;
        Map resultMap = new HashMap();
        // 8*4 位数字，加上七个: ipv6地址长度不可能超过39位
        if (address.length() > 39) {
            bool = false;
        } else if (IPV6_STD.matcher(address).matches()) {//标准格式判断
            bool = true;
        } else if (IPV6_COMPRESS.matcher(address).matches()) {//压缩发生在IP地址内部；唯一确定内容，IP地址首尾无冒号 (:)
            bool = true;
        }
        return bool;
    }

    public static boolean isIPv4Mask(String address) {
        return IPV4_MASK.matcher(address).matches();
    }

    public static boolean isIPv6Mask(String address) {
        return IPV6_MASK.matcher(address).matches();
    }

    public static String getIP(String url) {
        if (url == null) {
            return null;
        }
        int p = url.indexOf(StringPool.PROTOCOL_SYMBOL);
        if (p == -1) {
            return null;
        }
        String[] parts = url.split(StringPool.PROTOCOL_SYMBOL);
        String p1 = parts[1];
        String[] urlNoProtocol = p1.split(StringPool.SLASH);
        String host = urlNoProtocol[0];
        String[] hosts = host.split(StringPool.COLON);
        if (hosts.length == 2) {
            return hosts[0];
        }
        return host;
    }

    public static Integer getPort(String url) {
        if (url == null) {
            return null;
        }
        int p = url.indexOf(StringPool.PROTOCOL_SYMBOL);
        if (p == -1) {
            return null;
        }
        String[] parts = url.split(StringPool.PROTOCOL_SYMBOL);
        String p1 = parts[1];
        String[] urlNoProtocol = p1.split(StringPool.SLASH);
        String host = urlNoProtocol[0];
        String[] hosts = host.split(StringPool.COLON);
        if (hosts.length == 2) {
            return NumberUtil.toInt(hosts[1]);
        }
        return null;
    }

    public static String getSchema(String url) {
        if (url == null) {
            return null;
        }
        int p = url.indexOf(StringPool.PROTOCOL_SYMBOL);
        if (p == -1) {
            return null;
        }
        String[] parts = url.split(StringPool.PROTOCOL_SYMBOL);
        return parts[0];
    }
}

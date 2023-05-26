package com.hs.commons.lang;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class TimeUtil {
    public static final DateTimeFormatter YYYY_MM_DD_HH_MM_SS = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    public static final DateTimeFormatter YYYY_MM_DD_HH = DateTimeFormatter.ofPattern("yyyy-MM-dd HH");
    public static final DateTimeFormatter HH_MM = DateTimeFormatter.ofPattern("HH:mm");
    public static final DateTimeFormatter HH_MM_SS = DateTimeFormatter.ofPattern("HH:mm:ss");

    public static String now() {
        return getYearMonthDayHourMinuteSecond(LocalDateTime.now());
    }

    public static LocalDateTime toLocalDateTime(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    public static LocalDateTime toLocalDateTime(String date) {
        return LocalDateTime.parse(date, YYYY_MM_DD_HH_MM_SS);
    }

    public static String getYearMonthDayHourMinuteSecond(LocalDateTime localDateTime) {
        return localDateTime.format(YYYY_MM_DD_HH_MM_SS);
    }

    public static String getHourAndMinute(LocalDateTime localDateTime) {
        return localDateTime.format(HH_MM);
    }

    public static String getHourAndMinute(Date date) {
        return toLocalDateTime(date).format(HH_MM);
    }

    public static String getLocalDate(LocalDateTime localDateTime) {
        return localDateTime.toLocalDate().toString();
    }

    public static String getWeek(LocalDate localDate) {
        int week = localDate.getDayOfWeek().getValue();
        String result;
        switch (week) {
            case 1:
                result = "周一";
                break;
            case 2:
                result = "周二";
                break;
            case 3:
                result = "周三";
                break;
            case 4:
                result = "周四";
                break;
            case 5:
                result = "周五";
                break;
            case 6:
                result = "周六";
                break;
            default:
                result = "周日";
        }
        return result;
    }

    public static long getSeconds(LocalDateTime start, LocalDateTime end) {
        Duration duration = Duration.between(start, end);
        return duration.getSeconds();
    }
}

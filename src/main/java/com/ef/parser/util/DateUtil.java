package com.ef.parser.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtil {

    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");

    public static LocalDateTime toDate(String date){
        return LocalDateTime.parse(date, DATE_TIME_FORMATTER);
    }

    public static LocalDateTime toDate(String date, DateTimeFormatter dateTimeFormatter){
        return LocalDateTime.parse(date, dateTimeFormatter);
    }

    public static String format(LocalDateTime date){
        return date.format(DATE_TIME_FORMATTER);
    }


}

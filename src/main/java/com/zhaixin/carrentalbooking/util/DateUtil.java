package com.zhaixin.carrentalbooking.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


@Slf4j
public class DateUtil {

    /**
     * 获取当前时间前n分钟
     */
    public static Date getCurrentTimeAfterN(Date date, int n) {
        Calendar afterTime = Calendar.getInstance();
        afterTime.setTime(date);
        afterTime.add(Calendar.MINUTE, n);
        return afterTime.getTime();
    }

    /*
     * 根据当前日期，实现在当前日期的基础上往后推num天
     * */
    public static Date pushNumAfterDays(Date date, int num) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) + num);
        return calendar.getTime();
    }

}

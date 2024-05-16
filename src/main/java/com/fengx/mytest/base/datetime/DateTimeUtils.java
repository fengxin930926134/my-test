package com.fengx.mytest.base.datetime;

import org.apache.commons.lang3.StringUtils;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 日期时间工具类
 */
public class DateTimeUtils {

    /**
     * 日期格式化形式
     */
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter DATE_MONTH_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM");
    /**
     * 日期格式化形式
     */
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    /**
     * 日期格式化形式
     */
    private static final DateTimeFormatter DATE_TIME_MINUTE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    /**
     * 日期格式化形式
     */
    private static final DateTimeFormatter DATE_DIO_FORMATTER = DateTimeFormatter.ofPattern("yyyy.MM.dd");

    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");
    /**
     * 仅包含时分的匹配模式
     */
    public static final String SHORT_TIME_PATTERN = "HH:mm";

    /**
     * 根据匹配模式获取格式化对象
     *
     * @param pattern 匹配模式
     * @return DateTimeFormatter
     */
    public static DateTimeFormatter getFormatter(String pattern) {
        return DateTimeFormatter.ofPattern(pattern);
    }

    /**
     * 获取String的时分
     *
     * @param localDateTime LocalDateTime
     * @return String
     */
    public static String getHourMinute(LocalDateTime localDateTime) {
        if (localDateTime == null) {
            return "";
        }
        return getFormatter(SHORT_TIME_PATTERN).format(localDateTime);
    }

    public static String getHourMinute(LocalTime localTime) {
        if (localTime == null) {
            return "";
        }
        return getFormatter(SHORT_TIME_PATTERN).format(localTime);
    }

    private DateTimeUtils() {
    }

    /**
     * 日期转字符串
     *
     * @param date 日期
     * @return 字符串
     */
    public static String localDateToStr(LocalDate date) {
        if (Objects.isNull(date)) {
            return "";
        }

        return date.format(DATE_FORMATTER);
    }

    /**
     * 字符串转日期
     *
     * @param str 字符串
     * @return 日期
     */
    public static LocalDate strToLocalDate(String str) {
        if (StringUtils.isBlank(str)) {
            return null;
        }

        return LocalDate.parse(str, DATE_FORMATTER);
    }

    /**
     * 字符串转日期
     *
     * @param str 字符串
     * @return 日期
     */
    public static LocalTime strToLocalTime(String str) {
        if (StringUtils.isBlank(str)) {
            return null;
        }

        return LocalTime.parse(str, TIME_FORMATTER);
    }

    /**
     * 字符串转日期时间
     *
     * @param str 字符串
     * @return 日期
     */
    public static LocalDateTime strToLocalDateTime(String str) {
        if (StringUtils.isBlank(str)) {
            return null;
        }

        return LocalDateTime.parse(str, DATE_TIME_FORMATTER);
    }

    /**
     * 日期转字符串
     *
     * @param date 日期
     * @return 字符串
     */
    public static String localDateToMonthStr(LocalDate date) {
        if (Objects.isNull(date)) {
            return "";
        }

        return date.format(DATE_MONTH_FORMATTER);
    }

    /**
     * 日期转字符串
     *
     * @param date 日期
     * @return 字符串
     */
    public static String localDateToDioStr(LocalDate date) {
        if (Objects.isNull(date)) {
            return "";
        }

        return date.format(DATE_DIO_FORMATTER);
    }

    /**
     * 日期转字符串
     *
     * @param datetime 日期
     * @return 字符串
     */
    public static String localDateTimeToDateStr(LocalDateTime datetime) {
        if (Objects.isNull(datetime)) {
            return "";
        }

        return datetime.format(DATE_FORMATTER);
    }

    /**
     * 日期转字符串
     *
     * @param datetime 日期
     * @return 字符串
     */
    public static String localDateTimeToStr(LocalDateTime datetime) {
        if (Objects.isNull(datetime)) {
            return "";
        }

        return datetime.format(DATE_TIME_FORMATTER);
    }

    /**
     * 日期转字符串 - 分钟
     *
     * @param datetime 日期
     * @return 字符串
     */
    public static String localDateTimeToMinuteStr(LocalDateTime datetime) {
        if (Objects.isNull(datetime)) {
            return "";
        }

        return datetime.format(DATE_TIME_MINUTE_FORMATTER);
    }

    /**
     * 获取两个日期之前的月份时间
     *
     * @param startDate 开始日期
     * @param endDate   结束日期
     * @return 年月字符串
     */
    public static List<String> getBetweenYearMonthStr(LocalDate startDate, LocalDate endDate) {
        if (Objects.isNull(startDate) || Objects.isNull(endDate)) {
            return new ArrayList<>();
        }

        List<String> result = new ArrayList<>();
        while (startDate.isBefore(endDate)) {
            result.add(DateTimeUtils.localDateToMonthStr(startDate));
            startDate = startDate.plusMonths(1);
        }

        if (startDate.getMonth().getValue() == endDate.getMonth().getValue()) {
            result.add(DateTimeUtils.localDateToMonthStr(endDate));
        }

        return result;
    }

    /**
     * 获取两个日期转化为字符串
     *
     * @param startDate 开始日期
     * @param endDate   结束日期
     * @return 日期字符串
     */
    public static String betweenToStr(LocalDate startDate, LocalDate endDate) {
        if (Objects.isNull(startDate) || Objects.isNull(endDate)) {
            return "";
        }

        return DateTimeUtils.localDateToStr(startDate) + "到" + DateTimeUtils.localDateToStr(endDate);
    }

    /**
     * 获取两个日期转化为字符串
     *
     * @param startDate 开始日期
     * @param endDate   结束日期
     * @return 日期字符串
     */
    public static String betweenTimeToStr(LocalDateTime startDate, LocalDateTime endDate) {
        if (Objects.isNull(startDate) || Objects.isNull(endDate)) {
            return "";
        }

        return DateTimeUtils.localDateTimeToStr(startDate) + "到" + DateTimeUtils.localDateTimeToStr(endDate);
    }

    /**
     * 获取两个日期转化为字符串
     *
     * @param startDate 开始日期
     * @param endDate   结束日期
     * @return 日期字符串
     */
    public static String betweenTimeToMinuteStr(LocalDateTime startDate, LocalDateTime endDate) {
        if (Objects.isNull(startDate) || Objects.isNull(endDate)) {
            return "";
        }

        return DateTimeUtils.localDateTimeToMinuteStr(startDate) + "到" + DateTimeUtils.localDateTimeToMinuteStr(endDate);
    }

    /**
     * 获取年龄
     *
     * @param date 时间
     * @return 年龄
     */
    public static Integer getAge(LocalDate date) {
        if (Objects.isNull(date)) {
            return null;
        }

        return date.until(LocalDate.now()).getYears();
    }

    /**
     * 判断两个时间范围是否有交集(时间段末尾和开始相等不算做相交)
     *
     * @param dynaStartTime  比较时间段开始时间
     * @param dynaEndTime    比较时间段结束时间
     * @param fixedStartTime 参考时间段开始时间
     * @param fixedEndTime   参考时间段结束时间
     * @return 1.完全相交, 比较时间段被参考时间段包括 2.比较时间段包括参考时间段
     * 3.比较时间段后半部分在参考时间段内 4.比较时间段前半部分在参考时间内 5.不相交
     */
    public static Integer checkTimesHasOverlap(LocalTime dynaStartTime, LocalTime dynaEndTime, LocalTime fixedStartTime, LocalTime fixedEndTime) {
        if (dynaStartTime.compareTo(fixedStartTime) >= 0 && dynaEndTime.compareTo(fixedEndTime) <= 0) {
            return 1;
        } else if (dynaStartTime.compareTo(fixedStartTime) <= 0 && dynaEndTime.compareTo(fixedEndTime) >= 0) {
            return 2;
        } else if (dynaStartTime.compareTo(fixedStartTime) < 0 &&
                dynaEndTime.compareTo(fixedStartTime) > 0 && dynaEndTime.compareTo(fixedEndTime) < 0) {
            return 3;
        } else if (dynaStartTime.compareTo(fixedStartTime) > 0 && dynaStartTime.compareTo(fixedEndTime) < 0
                && fixedEndTime.compareTo(dynaEndTime) < 0) {
            return 4;
        } else {
            return 5;
        }
    }

    /**
     * 获取一个日期当前周的第几天的日期
     *
     * @param localDate 日期
     * @param days      第几天，1~7
     * @return 第几天的日期
     */
    public static LocalDate getDateOfWeekDays(LocalDate localDate, int days) {
        int value = localDate.getDayOfWeek().getValue();
        return localDate.plusDays(days - value);
    }

    /**
     * 获取一个日期当前月的第几天的日期
     *
     * @param localDate 日期
     * @param days      第几天，1~7
     * @return 第几天的日期, 如果不存在则返回null
     */
    public static LocalDate getDateOfMonthDays(LocalDate localDate, int days) {
        try {
            return LocalDate.of(localDate.getYear(), localDate.getMonth(), days);
        } catch (DateTimeException e) {
            e.printStackTrace();
            return null;
        }
    }

}

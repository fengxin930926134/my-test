package com.fengx.mytest.base.datetime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

public class Test {
    public static void main(String[] args) throws ParseException {
//        System.out.println("时间间隔");
//        // 2021-04-12 10:00 ~ 11:40
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//        LocalDateTime parse = LocalDateTime.parse("2021-04-12 10:00:00", formatter);
//        LocalDateTime parse2 = LocalDateTime.parse("2021-04-12 11:40:00", formatter);
//        Duration between = Duration.between(parse, parse2);
//        System.out.println(between.toMinutes());
//        LocalTime parse3 = LocalTime.parse("10:00:00");
//        LocalTime parse4 = LocalTime.parse("11:40:00");
//        Duration between2 = Duration.between(parse3, parse4);
//        System.out.println(between2.toMinutes());
//
//        LocalDateTime begin = LocalDateTime.of(2021, 1,28,0, 0);
//        LocalDateTime end = LocalDateTime.of(2021, 8,31,0, 0);
//
//
//        // 当前周第一天
//        System.out.println(LocalDateTime.now().with(DayOfWeek.MONDAY).toLocalDate().atStartOfDay());
//        // 当前周最后一天
//        System.out.println(LocalDateTime.now().with(DayOfWeek.SUNDAY).with(LocalTime.MAX));
//        // 上一周
//        System.out.println(LocalDateTime.now().with(DayOfWeek.MONDAY).toLocalDate().atStartOfDay().minus(1, ChronoUnit.WEEKS));
//        System.out.println(LocalDateTime.now().with(DayOfWeek.SUNDAY).with(LocalTime.MAX).minus(1, ChronoUnit.WEEKS));
//        // 获取月日
//        System.out.println(LocalDateTime.now().toLocalDate().getMonthValue() +" "+ LocalDateTime.now().toLocalDate().getDayOfMonth());
//        // 今年第几周
//        WeekFields weekFields = WeekFields.of(DayOfWeek.MONDAY,1);
//        System.out.println(LocalDateTime.now().with(DayOfWeek.MONDAY).minus(1, ChronoUnit.WEEKS).get(weekFields.weekOfYear()));
//        // 相差周
//        Duration between1 = Duration.between(begin, end);
//        long day = between1.toDays();
//        System.out.println(day);
//        System.out.println((int) (day / 7) + 1);
//        // 两个日期相差周
//        System.out.println(ChronoUnit.WEEKS.between(begin, end) + 1);
//        // 最大时间
//        System.out.println(LocalDateTime.now().with(LocalTime.MAX));

//        List<LocalDate> localDates = getAllDatesInTheDateRange(LocalDate.of(2014, 6, 1),
//                LocalDate.of(2016, 6, 1));
//        localDates.forEach(item -> {
//            System.out.println(item);
//        });
//        System.out.println(localDates.size());


//        /// 获取范围时间跳过工作日, 参数起始日期，实际天数
//        LocalDate start = LocalDate.of(2022, 11, 1);
//        LocalDate end = LocalDate.of(2022, 11, 30);
//        System.out.println(start + " " + end);
//        AtomicInteger index = new AtomicInteger(0);
//        long days = end.toEpochDay() - start.toEpochDay() + 1;
//        System.out.println(days);
//        int sum = Stream.generate(() -> start.plusDays(index.getAndIncrement())).limit(days).
//                filter(ld -> ld.getDayOfWeek() != DayOfWeek.SATURDAY && ld.getDayOfWeek() != DayOfWeek.SUNDAY)
//                .map(e -> 1).mapToInt(Integer::intValue).sum();
//        System.out.println(sum);
//
//        System.out.println(LocalDate.now().getDayOfWeek().getValue());

        // 日期格式转换
//        String date = "2月15日";
//        SimpleDateFormat format = new SimpleDateFormat("MM月dd日");
//        Date parse = format.parse(date);
//        System.out.println(parse.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
//        System.out.println(LocalDate.now().format(formatter));
        // 当年最小时间
//        System.out.println(LocalDate.now().with(TemporalAdjusters.firstDayOfYear()).atStartOfDay());


        //时间戳转yyyy-MM-dd HH:mm:ss
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(format.format(new Date(1672799400000L)) + " " + format.format(new Date(1672799700000L)));
    }

    /**
     * 获取时间范围内的所有日期
     * @param startDate
     * @param endDate
     * @return
     */
    private static List<LocalDate> getAllDatesInTheDateRange(LocalDate startDate, LocalDate endDate) {
        List<LocalDate> localDateList = new ArrayList<>();
        // 开始时间必须小于结束时间
        if (startDate.isAfter(endDate)) {
            return localDateList;
        }
        while (startDate.isBefore(endDate)) {
            localDateList.add(startDate);
            startDate = startDate.plusDays(1);
        }
        localDateList.add(endDate);
        return localDateList;
    }
}
package com.fengx.mytest.base.datetime;

import java.time.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

public class Test {
    public static void main(String[] args) {
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


        /// 获取范围时间跳过工作日, 参数起始日期，实际天数
        LocalDate start = LocalDate.of(2022, 11, 1);
        LocalDate end = LocalDate.of(2022, 11, 30);
        System.out.println(start + " " + end);
        AtomicInteger index = new AtomicInteger(0);
        long days = end.toEpochDay() - start.toEpochDay() + 1;
        System.out.println(days);
        int sum = Stream.generate(() -> start.plusDays(index.getAndIncrement())).limit(days).
                filter(ld -> ld.getDayOfWeek() != DayOfWeek.SATURDAY && ld.getDayOfWeek() != DayOfWeek.SUNDAY)
                .map(e -> 1).mapToInt(Integer::intValue).sum();
        System.out.println(sum);
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
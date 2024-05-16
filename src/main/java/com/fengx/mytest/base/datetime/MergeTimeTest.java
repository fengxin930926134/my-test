package com.fengx.mytest.base.datetime;

import com.google.common.collect.Lists;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MergeTimeTest {


    public static void main(String[] args) {
        TimeTableSubVO timetable1 = new TimeTableSubVO(LocalDate.parse("2023-01-01"), LocalTime.parse("08:00:00"), LocalTime.parse("10:00:00"), 2, 2);
        TimeTableSubVO timetable2 = new TimeTableSubVO(LocalDate.parse("2023-01-01"), LocalTime.parse("09:00:00"), LocalTime.parse("11:00:00"), 2, 3);
        TimeTableSubVO timetable3 = new TimeTableSubVO(LocalDate.parse("2023-01-01"), LocalTime.parse("12:00:00"), LocalTime.parse("14:00:00"), 2, 1);

        TimeTableSubVO timetable4 = new TimeTableSubVO(LocalDate.parse("2023-01-02"), LocalTime.parse("10:00:00"), LocalTime.parse("12:00:00"), 2, 4);
        TimeTableSubVO timetable5 = new TimeTableSubVO(LocalDate.parse("2023-01-02"), LocalTime.parse("11:00:00"), LocalTime.parse("13:00:00"), 2, 2);

        TimeTableSubVO timetable6 = new TimeTableSubVO(LocalDate.parse("2023-01-03"), LocalTime.parse("14:00:00"), LocalTime.parse("16:00:00"), 2, 3);
        TimeTableSubVO timetable7 = new TimeTableSubVO(LocalDate.parse("2023-01-03"), LocalTime.parse("15:00:00"), LocalTime.parse("17:00:00"), 2, 2);
        TimeTableSubVO timetable8 = new TimeTableSubVO(LocalDate.parse("2023-01-03"), LocalTime.parse("16:00:00"), LocalTime.parse("18:00:00"), 2, 1);

        // 边界数据
        TimeTableSubVO timetable9 = new TimeTableSubVO(LocalDate.parse("2023-01-04"), LocalTime.MIN, LocalTime.MAX, 2, 5);

        // 调用合并方法
        List<TimeTableSubVO> timetables = Arrays.asList(
                timetable1, timetable2, timetable3,
                timetable4, timetable5,
                timetable6, timetable7, timetable8,
                timetable9
        );
        List<TimeTableSubVO> mergedTimetables = mergeSubTime(timetables);

        // 输出合并结果
        for (TimeTableSubVO mergedTimetable : mergedTimetables) {
            System.out.println("Begin Time: " + mergedTimetable.getBeginTime() +
                    ", End Time: " + mergedTimetable.getEndTime() +
                    ", Number: " + mergedTimetable.getNumber());
        }
    }

    private static List<TimeTableSubVO> mergeSubTime(List<TimeTableSubVO> timetables) {
        if (CollectionUtils.isEmpty(timetables)) {
            return timetables;
        }
        List<TimeTableSubVO> tmp = Lists.newArrayList();
        // 去重
        List<TimeTableSubVO> values = Lists.newArrayList(
                timetables.stream().collect(Collectors.toMap(e -> e.getBeginTime().toString() + e.getEndTime(),
                        Function.identity(), (k1, k2) -> {
                            k1.setNumber(k1.getNumber() + k2.getNumber());
                            return k1;
                        })).values()
        );
        if (values.size() == 1) {
            return values;
        }
        values.sort(Comparator.comparing(TimeTableVO::getBeginTime));
        for (int i = 0; i < values.size() - 1; i++) {
            TimeTableSubVO timetable = values.get(i);
            TimeTableSubVO timetable1 = values.get(i + 1);
            Integer hasOverlap = DateTimeUtils.checkTimesHasOverlap(timetable.getBeginTime(), timetable.getEndTime(),
                    timetable1.getBeginTime(), timetable1.getEndTime());
            if (hasOverlap != 5) {
                List<TimeTableSubVO> collect = i + 2 < values.size() ? Stream.iterate(i + 2, e -> ++e).limit(values.size() - (i + 2))
                        .map(values::get).collect(Collectors.toList()) : Lists.newArrayList();
                switch (hasOverlap) {
                    case 1:
                        collect.add(
                                new TimeTableSubVO(
                                        timetable1.getLocalDate(),
                                        timetable1.getBeginTime(),
                                        timetable1.getEndTime(),
                                        3,
                                        timetable.getNumber() + timetable1.getNumber())
                        );
                        break;
                    case 2:
                        collect.add(
                                new TimeTableSubVO(
                                        timetable.getLocalDate(),
                                        timetable.getBeginTime(),
                                        timetable.getEndTime(),
                                        3,
                                        timetable.getNumber() + timetable1.getNumber())
                        );
                        break;
                    case 3:
                        collect.add(
                                new TimeTableSubVO(
                                        timetable.getLocalDate(),
                                        timetable.getBeginTime(),
                                        timetable1.getEndTime(),
                                        3,
                                        timetable.getNumber() + timetable1.getNumber())
                        );
                        break;
                    case 4:
                        collect.add(
                                new TimeTableSubVO(
                                        timetable.getLocalDate(),
                                        timetable1.getBeginTime(),
                                        timetable.getEndTime(),
                                        3,
                                        timetable.getNumber() + timetable1.getNumber())
                        );
                        break;
                    default:
                }
                collect.addAll(tmp);
                return mergeSubTime(collect);
            }
            tmp.add(timetable);
        }
        if (values.size() >= 2) {
            tmp.add(values.get(values.size() - 1));
        }
        return tmp;
    }

}

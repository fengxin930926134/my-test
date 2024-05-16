package com.fengx.mytest.base.datetime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TimeTableSubVO extends TimeTableVO {

    /**
     * 已预约人数（取时间并集）
     */
    private Integer number;

    public TimeTableSubVO(LocalDate localDate, LocalTime beginTime, LocalTime endTime, Integer type, Integer number) {
        super(localDate, beginTime, endTime, type);
        this.number = number;
    }

    public Integer getNumber() {
        return number == null ? 1 : number;
    }
}

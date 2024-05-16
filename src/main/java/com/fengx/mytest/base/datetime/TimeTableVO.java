package com.fengx.mytest.base.datetime;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * 每日开放时间表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TimeTableVO {
    /**
     * 对应日期
     */
    private LocalDate localDate;

    /**
     * 开始时间
     */
    @JsonFormat(pattern = "HH:mm:ss")
    @JSONField(format = "HH:mm")
    private LocalTime beginTime;
    /**
     * 结束时间
     */
    @JsonFormat(pattern = "HH:mm:ss")
    @JSONField(format = "HH:mm")
    private LocalTime endTime;

    /**
     * 0.时间段开放 1.时间段不开放 2.不能操作（时间已过去） 3.已被预约 4.预约已满
     */
    private Integer type;
}

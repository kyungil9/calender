package com.calender.data.mapper

import com.calender.data.model.local.DailyLocal
import com.calender.data.model.local.ScheduleLocal
import com.calender.domain.model.Daily
import com.calender.domain.model.Schedule



fun mapperToSchedule(scheduleLocals : List<ScheduleLocal>) : List<Schedule>{
    return scheduleLocals.toList().map {
        Schedule(
            it.date,
            it.timeSelect,
            it.startTime,
            it.endTime,
            it.repeat,
            it.tag,
            it.alarm,
            it.color,
            it.memoryDay,
            it.detail
        )
    }
}
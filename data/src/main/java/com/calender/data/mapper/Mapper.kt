package com.calender.data.mapper

import com.calender.data.model.local.DailyLocal
import com.calender.data.model.local.ScheduleLocal
import com.calender.domain.model.Daily
import com.calender.domain.model.Schedule

fun mapperToDaily(dailyLocals : List<DailyLocal>):List<Daily>{
    return dailyLocals.toList().map {
        Daily(
            it.date,
            it.record
        )
    }
}

fun mapperToSchedule(scheduleLocals : List<ScheduleLocal>) : List<Schedule>{
    return scheduleLocals.toList().map {
        Schedule(
            it.date,
            it.time,
            it.detail
        )
    }
}
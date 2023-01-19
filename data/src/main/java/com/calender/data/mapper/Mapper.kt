package com.calender.data.mapper

import com.calender.data.model.local.DailyLocal
import com.calender.data.model.local.ScheduleLocal
import com.calender.data.model.local.ToDoCheckLocal
import com.calender.domain.model.Daily
import com.calender.domain.model.Schedule
import com.calender.domain.model.ToDoCheck


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

fun mapperToToDo(todoLocals : List<ToDoCheckLocal>) : List<ToDoCheck>{
    return todoLocals.toList().map {
        ToDoCheck(
            it.date,
            it.doIt,
            it.tag,
            it.repeat,
            it.state,
            it.statePercent,
            it.period,
            it.endDate,
            it.alarm
        )
    }
}
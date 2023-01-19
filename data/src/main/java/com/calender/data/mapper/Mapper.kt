package com.calender.data.mapper

import com.calender.data.model.local.*
import com.calender.domain.model.*


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

fun mapperToMemo(memoLocals : List<MemoLocal>) : List<Memo>{
    return memoLocals.toList().map {
        Memo(
            it.title,
            it.detail,
            it.tag
        )
    }
}

fun mapperToRecord(recordLocals : List<RecordLocal>) : List<Record>{
    return recordLocals.toList().map {
        Record(
            it.tag,
            it.startTime,
            it.endTime,
            it.progressTime,
            it.check
        )
    }
}
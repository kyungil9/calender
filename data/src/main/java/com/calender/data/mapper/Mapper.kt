package com.calender.data.mapper

import com.calender.data.model.local.*
import com.calender.domain.model.*
import java.time.LocalDateTime
import java.time.ZoneOffset


fun mapperToScheduleList(scheduleLocals : List<ScheduleLocal>) : List<Schedule>{
    return scheduleLocals.toList().map {
        Schedule(
            it.id,
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
fun mapperToSchedule(it : ScheduleLocal) : Schedule{
    return Schedule(
            it.id,
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

fun mapperToScheduleLocal(it : Schedule) : ScheduleLocal{
    return ScheduleLocal(
        it.id,
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
fun mapperToToDo(todoLocals : List<ToDoCheckLocal>) : List<ToDoCheck>{
    return todoLocals.toList().map {
        ToDoCheck(
            it.id,
            it.date,
            it.doIt,
            it.tag,
            it.repeat,
            it.state,
            it.statePercent,
            it.endDate,
            it.alarm
        )
    }
}

fun mapperToToDoCheck(it : ToDoCheckLocal) : ToDoCheck{
    return ToDoCheck(
            it.id,
            it.date,
            it.doIt,
            it.tag,
            it.repeat,
            it.state,
            it.statePercent,
            it.endDate,
            it.alarm)
}

fun mapperToArrayToDo(todoLocals : List<ToDoCheckLocal>) : ArrayList<ToDoCheck>{
    val arrayToDo = ArrayList<ToDoCheck>()
    val todoCheck = todoLocals.toList().map {
        ToDoCheck(
            it.id,
            it.date,
            it.doIt,
            it.tag,
            it.repeat,
            it.state,
            it.statePercent,
            it.endDate,
            it.alarm
        )
    }
    arrayToDo.addAll(todoCheck)
    return arrayToDo
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
            it.id,
            it.tag,
            it.startTime,
            it.endTime!!,
            it.progressTime,
            it.check
        )
    }
}

fun mapperToToDoLocal(todo : ToDoCheck) : ToDoCheckLocal{
    return ToDoCheckLocal(
        id = todo.id,
        date = todo.date,
        doIt = todo.doIt,
        tag = todo.tag,
        repeat = todo.repeat,
        state = todo.state,
        statePercent = todo.statePercent,
        endDate = todo.endDate,
        alarm = todo.alarm
    )
}

fun mapperToTagLocal(tag : String, mode: Int) : TagLocal{
    return TagLocal(
        tag = tag,
        mode = mode
    )
}

fun mapperToTag(tags : List<TagLocal>) : List<String>{
    return tags.toList().map{
        it.tag
    }
}

fun mapperToDateTimetoLong(date : LocalDateTime) : Long {
    return date.atZone(ZoneOffset.UTC).toInstant().toEpochMilli()
}

fun mapperToRecordLocal(record: Record) : RecordLocal {
    return RecordLocal(
            tag = record.tag,
            startTime = record.startTime,
            endTime = record?.endTime,
            progressTime = record.progressTime,
            check = record.check
    )
}

fun mapperToRecordSingle(record: RecordLocal) : Record{
    return Record(
        id = record.id,
        tag = record.tag,
        startTime = record.startTime,
        endTime = record?.endTime,
        progressTime = record.progressTime,
        check = record.check
    )
}
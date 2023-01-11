package com.calender.data.model.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate
import java.time.LocalTime
@Entity(tableName = "schedule")
data class ScheduleLocal(
    @PrimaryKey var date: LocalDate = LocalDate.now(),
    var time : LocalTime,
    var detail : String
) {
}
package com.calender.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate
import java.time.LocalTime
@Entity(tableName = "schedule")
data class Schedule(
    @PrimaryKey var date: LocalDate = LocalDate.now(),
    var time : LocalTime,
    var detail : String
) {
}
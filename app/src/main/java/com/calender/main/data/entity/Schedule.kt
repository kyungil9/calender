package com.calender.main.data.entity

import java.time.LocalDate
import java.time.LocalTime

data class Schedule(
    val date: LocalDate,
    val time : LocalTime,
    val detail : String
) {
}
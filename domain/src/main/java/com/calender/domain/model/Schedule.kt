package com.calender.domain.model

import java.time.LocalDate
import java.time.LocalTime

data class Schedule(
    var date: LocalDate,
    var time : LocalTime,
    var detail : String
) {
}
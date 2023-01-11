package com.calender.data.model.local

import java.time.LocalDate

data class CalenderLocal(
    val month : LocalDate,
    var dailies : ArrayList<DailyLocal>
)

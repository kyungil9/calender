package com.calender.main.data.entity

import java.time.LocalDate

data class Calender(
    val month : LocalDate,
    var dailies : ArrayList<Daily>
)

package com.calender.data.model

import java.time.LocalDate

data class Calender(
    val month : LocalDate,
    var dailies : ArrayList<Daily>
)

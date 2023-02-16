package com.calender.domain.model

import java.time.LocalDate

data class Calender(
    val month : LocalDate,
    val list : ArrayList<Daily>
)

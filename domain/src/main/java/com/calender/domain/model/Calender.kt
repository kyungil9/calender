package com.calender.domain.model

import java.time.LocalDate

data class Calender(
    val month : LocalDate = LocalDate.now(),
    val list : ArrayList<Daily> = arrayListOf()
)

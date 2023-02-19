package com.calender.domain.model

import java.time.LocalDate

data class Daily(
    val date: LocalDate = LocalDate.now(),
    val list: ArrayList<Schedule> = arrayListOf()
)

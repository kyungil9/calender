package com.calender.domain.model

import java.time.LocalDate

data class Daily(
    val date: LocalDate,
    val list: ArrayList<Schedule>
)

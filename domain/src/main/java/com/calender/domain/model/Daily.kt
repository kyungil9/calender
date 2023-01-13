package com.calender.domain.model

import java.time.LocalDate

data class Daily(
    val date : LocalDate,
    var list : ArrayList<Schedule>
)

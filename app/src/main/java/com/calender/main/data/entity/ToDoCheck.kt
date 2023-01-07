package com.calender.main.data.entity

import java.time.LocalDate

data class ToDoCheck(
    val date : LocalDate,
    var doIt : String,
    var check : Boolean
)

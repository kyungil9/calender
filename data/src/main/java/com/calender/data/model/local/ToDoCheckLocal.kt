package com.calender.data.model.local

import java.time.LocalDate

data class ToDoCheckLocal(
    val date : LocalDate,
    var doIt : String,
    var check : Boolean
)

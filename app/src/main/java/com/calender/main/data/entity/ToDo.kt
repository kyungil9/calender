package com.calender.main.data.entity

import java.time.LocalDate

data class ToDo(
    val date :LocalDate,
    var list : ArrayList<ToDoCheck>
)

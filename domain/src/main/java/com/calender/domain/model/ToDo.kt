package com.calender.domain.model
import java.time.LocalDate

data class ToDo(
    val date :LocalDate,
    var list : ArrayList<ToDoCheck>
)
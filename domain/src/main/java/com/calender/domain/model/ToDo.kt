package com.calender.domain.model
import java.time.LocalDate

data class ToDo(
    val date :LocalDate = LocalDate.now(),
    val title : String = "",
    var list : ArrayList<ToDoCheck> = ArrayList<ToDoCheck>()
)

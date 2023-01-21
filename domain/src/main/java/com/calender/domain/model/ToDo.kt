package com.calender.domain.model
import java.time.LocalDate

data class ToDo(
    val date :LocalDate = LocalDate.now(),
    val title : String = "",
    var list : List<ToDoCheck> = emptyList<ToDoCheck>()
)

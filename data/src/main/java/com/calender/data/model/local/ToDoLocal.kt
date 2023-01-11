package com.calender.data.model.local

import com.calender.data.model.local.ToDoCheckLocal
import java.time.LocalDate

data class ToDoLocal(
    val date :LocalDate,
    var list : ArrayList<ToDoCheckLocal>
)

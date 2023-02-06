package com.calender.presentation.listener

import com.calender.domain.model.ToDoCheck
import com.calender.domain.model.ToDoCheckMode
import java.time.LocalDate

interface RecyclerViewToDoClickListener {
    fun onItemClickListener(item : ToDoCheck,mode : ToDoCheckMode)
}
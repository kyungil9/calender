package com.calender.data.repository.local.interfaces

import com.calender.data.model.local.ToDoCheckLocal
import com.calender.domain.model.Result
import com.calender.domain.model.ToDo
import kotlinx.coroutines.flow.Flow

interface ToDoLocalDataSource {
    fun getAllToDo() : List<ToDoCheckLocal>

    fun getProgramToDo() : Flow<Result<List<ToDo>>>

    fun getDateToDo() : Flow<Result<List<ToDo>>>

    fun insertToDo(todo : ToDoCheckLocal)
}
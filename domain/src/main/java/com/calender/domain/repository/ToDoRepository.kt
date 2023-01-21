package com.calender.domain.repository

import com.calender.domain.model.Result
import com.calender.domain.model.ToDo
import com.calender.domain.model.ToDoCheck
import kotlinx.coroutines.flow.Flow

interface ToDoRepository {
    fun getAllToDo():List<ToDoCheck>

    fun getProgramToDo(): Flow<Result<List<ToDo>>>

    fun getDateToDo():Flow<Result<List<ToDo>>>
}
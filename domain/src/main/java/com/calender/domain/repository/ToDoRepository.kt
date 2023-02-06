package com.calender.domain.repository

import com.calender.domain.model.Result
import com.calender.domain.model.ToDo
import com.calender.domain.model.ToDoCheck
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

interface ToDoRepository {
    fun getAllToDo():List<ToDoCheck>

    fun getProgramToDo(): Flow<Result<List<ToDo>>>

    fun getDateToDo():Flow<Result<List<ToDo>>>

    fun insertToDo(todo : ToDoCheck)
    fun updateToDoState(state : Int,id : Int)

    fun updateToDoStatePercent(statePercent : Int,id : Int)
}
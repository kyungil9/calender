package com.calender.data.repository.local.interfaces

import com.calender.data.model.local.ToDoCheckLocal
import com.calender.domain.model.Result
import com.calender.domain.model.ToDo
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

interface ToDoLocalDataSource {
    fun getAllToDo() : List<ToDoCheckLocal>

    fun getProgramToDo() : Flow<Result<List<ToDo>>>

    fun getDateToDo() : Flow<Result<List<ToDo>>>

    fun insertToDo(todo : ToDoCheckLocal)

    fun updateToDoState(state : Int,id : Int)

    fun updateToDoStatePercent(statePercent : Int,id : Int)

    fun getOneDateToDo(date: LocalDate) : Flow<Result<ToDo>>
}
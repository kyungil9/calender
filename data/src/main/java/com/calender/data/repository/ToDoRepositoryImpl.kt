package com.calender.data.repository

import com.calender.data.mapper.mapperToToDo
import com.calender.data.repository.local.interfaces.ToDoLocalDataSource
import com.calender.domain.model.Result
import com.calender.domain.model.ToDo
import com.calender.domain.model.ToDoCheck
import com.calender.domain.repository.ToDoRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ToDoRepositoryImpl @Inject constructor(
    private val toDoLocalData : ToDoLocalDataSource
):ToDoRepository {
    override fun getAllToDo(): List<ToDoCheck> {
        return mapperToToDo(toDoLocalData.getAllToDo())
    }

    override fun getDateToDo(): Flow<Result<List<ToDo>>> {
        return toDoLocalData.getDateToDo()
    }

    override fun getProgramToDo(): Flow<Result<List<ToDo>>> {
        return toDoLocalData.getProgramToDo()
    }
}
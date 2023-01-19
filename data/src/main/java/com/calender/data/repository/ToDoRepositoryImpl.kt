package com.calender.data.repository

import com.calender.data.mapper.mapperToToDo
import com.calender.data.repository.local.interfaces.ToDoLocalDataSource
import com.calender.domain.model.ToDoCheck
import com.calender.domain.repository.ToDoRepository
import javax.inject.Inject

class ToDoRepositoryImpl @Inject constructor(
    private val toDoLocalData : ToDoLocalDataSource
):ToDoRepository {
    override fun getAllToDo(): List<ToDoCheck> {
        return mapperToToDo(toDoLocalData.getAllToDo())
    }
}
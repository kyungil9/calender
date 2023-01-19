package com.calender.data.repository.local.impl

import com.calender.data.database.dao.TodoDao
import com.calender.data.model.local.ToDoCheckLocal
import com.calender.data.repository.local.interfaces.ToDoLocalDataSource
import javax.inject.Inject

class ToDoLocalDataSourceImpl @Inject constructor(
    private val todoDao: TodoDao
) : ToDoLocalDataSource{
    override fun getAllToDo(): List<ToDoCheckLocal> {
        return todoDao.getAllTodoInfo()
    }
}
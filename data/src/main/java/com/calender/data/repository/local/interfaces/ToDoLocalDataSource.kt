package com.calender.data.repository.local.interfaces

import com.calender.data.model.local.ToDoCheckLocal

interface ToDoLocalDataSource {
    fun getAllToDo() : List<ToDoCheckLocal>
}
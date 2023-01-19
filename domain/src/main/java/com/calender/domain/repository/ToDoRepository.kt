package com.calender.domain.repository

import com.calender.domain.model.ToDoCheck

interface ToDoRepository {

    fun getAllToDo():List<ToDoCheck>
}
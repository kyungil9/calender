package com.calender.domain.usecase.todo

import com.calender.domain.model.ToDoCheck
import com.calender.domain.repository.ToDoRepository
import javax.inject.Inject

class InsertToDoUseCase @Inject constructor(
    private val toDoRepository: ToDoRepository
){
    operator fun invoke(todo : ToDoCheck){
        return toDoRepository.insertToDo(todo)
    }
}
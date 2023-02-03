package com.calender.domain.usecase.todo

import com.calender.domain.model.ToDoCheck
import com.calender.domain.repository.ToDoRepository
import javax.inject.Inject

class GetAllToDoUseCase @Inject constructor(
    private val toDoRepository: ToDoRepository
){
    operator fun invoke() : List<ToDoCheck>{
        return toDoRepository.getAllToDo()
    }
}
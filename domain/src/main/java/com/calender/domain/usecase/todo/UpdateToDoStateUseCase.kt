package com.calender.domain.usecase.todo

import com.calender.domain.repository.ToDoRepository
import javax.inject.Inject

class UpdateToDoStateUseCase @Inject constructor(
    private val toDoRepository: ToDoRepository
) {
    operator fun invoke(state : Int,id:Int){
        toDoRepository.updateToDoState(state,id)
    }
}
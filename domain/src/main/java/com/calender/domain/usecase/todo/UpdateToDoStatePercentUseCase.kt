package com.calender.domain.usecase.todo

import com.calender.domain.repository.ToDoRepository
import javax.inject.Inject

class UpdateToDoStatePercentUseCase @Inject constructor(
    private val toDoRepository: ToDoRepository
) {
    operator fun invoke(statePercent : Int,id:Int){
        toDoRepository.updateToDoStatePercent(statePercent,id)
    }
}
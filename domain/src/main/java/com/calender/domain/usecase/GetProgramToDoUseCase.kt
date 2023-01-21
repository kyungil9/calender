package com.calender.domain.usecase

import com.calender.domain.model.Result
import com.calender.domain.model.ToDo
import com.calender.domain.repository.ToDoRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetProgramToDoUseCase @Inject constructor(
    private val toDoRepository: ToDoRepository
) {
    operator fun invoke() : Flow<Result<List<ToDo>>> {
        return toDoRepository.getProgramToDo()
    }
}
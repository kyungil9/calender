package com.calender.domain.usecase.todo

import com.calender.domain.model.Result
import com.calender.domain.model.ToDo
import com.calender.domain.repository.ToDoRepository
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate
import javax.inject.Inject

class GetOneDateToDoUseCase @Inject constructor(
    private val toDoRepository: ToDoRepository
) {
    operator fun invoke(date: LocalDate) : Flow<Result<ToDo>>{
        return toDoRepository.getOneDateToDo(date)
    }
}
package com.calender.data.repository

import com.calender.data.mapper.mapperToToDo
import com.calender.data.mapper.mapperToToDoLocal
import com.calender.data.repository.local.interfaces.ToDoLocalDataSource
import com.calender.domain.model.Result
import com.calender.domain.model.ToDo
import com.calender.domain.model.ToDoCheck
import com.calender.domain.repository.ToDoRepository
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate
import javax.inject.Inject
import javax.inject.Singleton


class ToDoRepositoryImpl @Inject constructor(
    private val toDoLocalData : ToDoLocalDataSource
):ToDoRepository {
    override fun getAllToDo(): List<ToDoCheck> {
        return mapperToToDo(toDoLocalData.getAllToDo())
    }

    override fun getDateToDo(): Flow<Result<List<ToDo>>> {
        return toDoLocalData.getDateToDo()
    }

    override fun getProgramToDo(): Flow<Result<List<ToDo>>> {
        return toDoLocalData.getProgramToDo()
    }

    override fun insertToDo(todo : ToDoCheck) {
        toDoLocalData.insertToDo(mapperToToDoLocal(todo))
    }
    override fun updateToDoState(state : Int,id : Int){
        toDoLocalData.updateToDoState(state,id)
    }

    override fun updateToDoStatePercent(statePercent: Int, id : Int) {
        toDoLocalData.updateToDoStatePercent(statePercent,id)
    }

    override fun getOneDateToDo(date: LocalDate): Flow<Result<ToDo>> {
        return toDoLocalData.getOneDateToDo(date)
    }
}
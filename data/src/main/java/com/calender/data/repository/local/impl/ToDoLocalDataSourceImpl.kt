package com.calender.data.repository.local.impl

import com.calender.data.database.dao.TodoDao
import com.calender.data.mapper.mapperToToDo
import com.calender.data.model.local.ToDoCheckLocal
import com.calender.data.repository.local.interfaces.ToDoLocalDataSource
import com.calender.domain.model.Result
import com.calender.domain.model.ToDo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import java.time.LocalDate
import javax.inject.Inject

class ToDoLocalDataSourceImpl @Inject constructor(
    private val todoDao: TodoDao
) : ToDoLocalDataSource{
    override fun getAllToDo(): List<ToDoCheckLocal> {
        return todoDao.getAllTodoInfo()
    }

//    override fun getProgramToDo(): List<ToDo> {
//        val tags = todoDao.getTodoTagListInfo()
//        val todoList = ArrayList<ToDo>()
//        for (tag in tags){
//            val list = todoDao.getProgramTodoInfo(tag)
//            todoList.add(ToDo(LocalDate.now(),tag, mapperToToDo(list)))
//        }
//        return todoList
//    }
    override fun getProgramToDo():Flow<Result<List<ToDo>>> = flow<Result<List<ToDo>>> {
        emit(Result.Loading)
        val tags = todoDao.getTodoTagListInfo()
        val todoList = ArrayList<ToDo>()
        if (tags.isEmpty()){
            emit(Result.Empty)
        }else {
            for (tag in tags) {
                val list = todoDao.getProgramTodoInfo(tag)
                todoList.add(ToDo(LocalDate.now(), tag, mapperToToDo(list)))
            }
            emit(Result.Success(todoList))
        }
    }.catch { e->
        emit(Result.Error(e))
    }

    override fun getDateToDo(): Flow<Result<List<ToDo>>> = flow<Result<List<ToDo>>> {
        emit(Result.Loading)
        val dates = todoDao.getTodoDateListInfo()
        val todoList = ArrayList<ToDo>()
        if(dates.isEmpty()){
            emit(Result.Empty)
        }else {
            for (date in dates) {
                val list = todoDao.getDateTodoInfo(date)
                todoList.add(ToDo(date, "", mapperToToDo(list)))
            }
            emit(Result.Success(todoList))
        }
    }.catch { e->
        emit(Result.Error(e))
    }

    override fun insertToDo(todo: ToDoCheckLocal) {
        todoDao.insertTodoInfo(todo)
    }


}
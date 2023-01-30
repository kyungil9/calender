package com.calender.data.repository.local.impl

import android.util.Log
import com.calender.data.database.dao.TodoDao
import com.calender.data.mapper.mapperToToDo
import com.calender.data.model.local.ToDoCheckLocal
import com.calender.data.repository.local.interfaces.ToDoLocalDataSource
import com.calender.domain.model.Result
import com.calender.domain.model.ToDo
import com.calender.domain.model.successOrNull
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.flow.internal.ChannelFlow
import java.time.LocalDate
import javax.inject.Inject

class ToDoLocalDataSourceImpl @Inject constructor(
    private val todoDao: TodoDao
) : ToDoLocalDataSource{
    override fun getAllToDo(): List<ToDoCheckLocal> {
        //return todoDao.getAllTodoInfo()
        TODO()
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
    override fun getProgramToDo(): Flow<Result<List<ToDo>>> = channelFlow<Result<List<ToDo>>> {
        send(Result.Loading)
        val todoList = ArrayList<ToDo>()
        todoDao.getAllTodoInfo().collectLatest {
            Log.d("test",it.toString())
            if(it.isEmpty())
                send(Result.Empty)
            else {
                todoList.add(ToDo(LocalDate.now(), "tag", mapperToToDo(it)))
                send(Result.Success(todoList))
            }
        }

//        todoDao.getTodoTagListInfo().collectLatest {
//            if (it.isEmpty())
//                emit(Result.Empty)
//            else{
//                Log.d("test",it.toString())
//                for (tag in it) {
//                    todoDao.getProgramTodoInfo(tag).collectLatest { todo->
//                        Log.d("test",todo.toString())
//                        if(todo.isEmpty())
//                            emit(Result.Empty)
//                        else
//                            todoList.add(ToDo(LocalDate.now(), tag, mapperToToDo(todo)))
//                    }
//                }
//                Log.d("test",todoList.toString())
//                emit(Result.Success(todoList))
//            }
//        }
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
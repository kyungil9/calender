package com.calender.data.repository.local.impl

import com.calender.data.database.dao.TodoDao
import com.calender.data.mapper.mapperToToDoCheck
import com.calender.data.model.local.ToDoCheckLocal
import com.calender.data.repository.local.interfaces.ToDoLocalDataSource
import com.calender.domain.model.Result
import com.calender.domain.model.ToDo
import kotlinx.coroutines.flow.*
import java.time.LocalDate
import javax.inject.Inject
import javax.inject.Singleton


class ToDoLocalDataSourceImpl @Inject constructor(
    private val todoDao: TodoDao
) : ToDoLocalDataSource{
    override fun getAllToDo(): List<ToDoCheckLocal> {
        //return todoDao.getAllTodoInfo()
        TODO()
    }


    override fun getProgramToDo(): Flow<Result<List<ToDo>>> = channelFlow<Result<List<ToDo>>> {
        todoDao.getProgramTodoInfo().collectLatest { todo->
            if(todo.isEmpty())
                send(Result.Empty)
            else {
                val todoList = ArrayList<ToDo>()
                var i =0
                todoList.add(ToDo(LocalDate.now(), todo[0].tag, arrayListOf(mapperToToDoCheck(todo[0]))))
                for (list in todo){
                    if (todo.indexOf(list) == 0)
                        continue
                    if(todoList[i].title == list.tag){
                        todoList[i].list.add(mapperToToDoCheck(list))
                    }else{
                        i++
                        todoList.add(ToDo(LocalDate.now(), list.tag, arrayListOf(mapperToToDoCheck(list))))
                    }
                }
                send(Result.Success(todoList))
            }
        }
    }.catch { e->
        emit(Result.Error(e))
    }

    override fun getDateToDo(): Flow<Result<List<ToDo>>> = channelFlow<Result<List<ToDo>>> {
        todoDao.getDateTodoInfo().collectLatest { todo->
            if(todo.isEmpty())
                send(Result.Empty)
            else {
                val todoList = ArrayList<ToDo>()
                var i =0
                todoList.add(ToDo(todo[0].date, "", arrayListOf(mapperToToDoCheck(todo[0]))))
                for (list in todo){
                    if (todo.indexOf(list) == 0)
                        continue
                    if(todoList[i].date == list.date){
                        todoList[i].list.add(mapperToToDoCheck(list))
                    }else{
                        i++
                        todoList.add(ToDo(list.date, "", arrayListOf(mapperToToDoCheck(list))))
                    }
                }
                send(Result.Success(todoList))
            }
        }

    }.catch { e->
        emit(Result.Error(e))
    }

    override fun getOneDateToDo(date: LocalDate): Flow<Result<ToDo>> = channelFlow<Result<ToDo>> {
        todoDao.getOneDateTodoInfo(date).collectLatest { todo->
            if(todo.isEmpty())
                send(Result.Empty)
            else
                send(Result.Success(ToDo(todo[0].date, "", arrayListOf(mapperToToDoCheck(todo[0])))))
            }
    }.catch { e->
        emit(Result.Error(e))
    }

    override fun insertToDo(todo: ToDoCheckLocal) {
        todoDao.insertTodoInfo(todo)
    }

    override fun updateToDoState(state: Int, id : Int) {
        todoDao.updateTodoState(state,id)
    }

    override fun updateToDoStatePercent(statePercent: Int,id : Int) {
        todoDao.updateTodoStatePercent(statePercent,id)
    }


}
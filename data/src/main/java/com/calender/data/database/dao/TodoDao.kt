package com.calender.data.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.calender.data.model.local.ToDoCheckLocal
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

@Dao
interface TodoDao {

    @Query("select * from todo")
    fun getAllTodoInfo():Flow<List<ToDoCheckLocal>>

    @Query("select tag from todo group by tag")
    fun getTodoTagListInfo():Flow<List<String>>

    @Query("select date from todo group by date")
    fun getTodoDateListInfo():List<LocalDate>

    @Query("select * from todo where tag in (select tag from todo group by tag) order by tag asc")
    fun getProgramTodoInfo():Flow<List<ToDoCheckLocal>>

    @Query("select * from todo where date in (select date from todo group by date) order by date asc")
    fun getDateTodoInfo():Flow<List<ToDoCheckLocal>>

    @Query("select * from todo where date = :date order by date asc")
    fun getOneDateTodoInfo(date: LocalDate):Flow<List<ToDoCheckLocal>>

    @Insert
    fun insertTodoInfo(todo : ToDoCheckLocal)

    @Query("update todo SET state=:mState where id =:id")
    fun updateTodoState(mState : Int,id : Int)

    @Query("update todo SET statePercent=:mStatePercent where id =:id")
    fun updateTodoStatePercent(mStatePercent : Int,id : Int)
}
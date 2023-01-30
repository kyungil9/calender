package com.calender.data.database.dao

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

    @Query("select * from todo where tag = :title")
    fun getProgramTodoInfo(title : String):Flow<List<ToDoCheckLocal>>

    @Query("select * from todo where date = :date")
    fun getDateTodoInfo(date : LocalDate):List<ToDoCheckLocal>

    @Insert
    fun insertTodoInfo(todo : ToDoCheckLocal)


}
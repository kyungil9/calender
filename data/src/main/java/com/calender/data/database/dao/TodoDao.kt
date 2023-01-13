package com.calender.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.calender.data.model.local.ToDoCheckLocal

@Dao
interface TodoDao {

    @Query("select * from todo")
    fun getTodoInfo():List<ToDoCheckLocal>
}
package com.calender.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.calender.data.model.local.MemoLocal

@Dao
interface MemoDao {

    @Query("select * from memo")
    fun getAllMemoInfo():List<MemoLocal>
}
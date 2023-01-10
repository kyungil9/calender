package com.calender.data.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.calender.data.model.Daily


@Dao
interface DailyDao {
    @Query("select * from daily")
    fun getAllDailyInfo():List<Daily>

}
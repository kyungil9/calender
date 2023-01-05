package com.calender.main.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.calender.main.data.entity.Daily

@Dao
interface DailyDao {
    @Query("select * from daily")
    fun getAllDailyInfo():LiveData<List<Daily>>

}
package com.calender.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.calender.data.model.local.DailyLocal


@Dao
interface DailyDao {
    @Query("select * from daily")
    fun getAllDailyInfo():List<DailyLocal>

}
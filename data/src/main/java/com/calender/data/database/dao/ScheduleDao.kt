package com.calender.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.calender.data.model.Schedule
import java.time.LocalDate

@Dao
interface ScheduleDao {
    @Query("select * from schedule where date = :today")
    fun getSearchSchedule(today : LocalDate) : List<Schedule>

}
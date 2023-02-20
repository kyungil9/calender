package com.calender.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.calender.data.model.local.ScheduleLocal
import com.calender.domain.model.Schedule
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

@Dao
interface ScheduleDao {
    @Query("select * from schedule where date = :today")
    fun getSearchSchedule(today : LocalDate) : List<ScheduleLocal>

    @Query("select * from schedule where date >= :startDate and date <= :endDate order by date asc")
    fun getMonthSchedule(startDate : LocalDate , endDate: LocalDate) : Flow<List<ScheduleLocal>>

    @Insert
    fun insertSchedule(schedule : ScheduleLocal)

    @Update
    fun updateSchedule(schedule: ScheduleLocal)
}
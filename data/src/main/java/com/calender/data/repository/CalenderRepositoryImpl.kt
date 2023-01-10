package com.calender.data.repository

import com.calender.data.database.dao.DailyDao
import com.calender.data.database.dao.ScheduleDao
import com.calender.data.model.Daily
import com.calender.data.model.Schedule
import com.calender.domain.repository.CalenderRepository
import java.time.LocalDate
import javax.inject.Inject

class CalenderRepositoryImpl @Inject constructor(
    private val dailyDao : DailyDao,
    private val scheduleDao : ScheduleDao
    ): CalenderRepository {


    override fun getAllDailyData(): List<Daily> { return dailyDao.getAllDailyInfo()}

    override fun getSearchSchedule(date : LocalDate):List<Schedule> { return scheduleDao.getSearchSchedule(date)}

}
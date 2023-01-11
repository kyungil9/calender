package com.calender.data.repository

import com.calender.data.mapper.mapperToDaily
import com.calender.data.mapper.mapperToSchedule
import com.calender.data.repository.local.interfaces.DailyLocalDataSource
import com.calender.data.repository.local.interfaces.ScheduleLocalDataSource
import com.calender.domain.model.Daily
import com.calender.domain.model.Schedule
import com.calender.domain.repository.CalenderRepository
import java.time.LocalDate
import javax.inject.Inject

class CalenderRepositoryImpl @Inject constructor(
    private val dailyLocalData : DailyLocalDataSource,
    private val scheduleLocalData : ScheduleLocalDataSource
    ): CalenderRepository {


    override fun getAllDailyData(): List<Daily> {
        return mapperToDaily(dailyLocalData.getAllDailys())
    }

    override fun getSearchSchedule(date : LocalDate):List<Schedule> {
        return mapperToSchedule(scheduleLocalData.getSearchSchedule(date))
    }

}
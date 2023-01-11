package com.calender.data.repository.local.impl

import com.calender.data.database.dao.ScheduleDao
import com.calender.data.model.local.ScheduleLocal
import com.calender.data.repository.local.interfaces.ScheduleLocalDataSource
import java.time.LocalDate
import javax.inject.Inject

class ScheduleLocalDataSourceImpl @Inject constructor(private val scheduleDao: ScheduleDao) : ScheduleLocalDataSource {
    override fun getSearchSchedule(date: LocalDate): List<ScheduleLocal> {
        return scheduleDao.getSearchSchedule(date)
    }
}
package com.calender.data.repository

import com.calender.data.mapper.mapperToSchedule
import com.calender.data.repository.local.interfaces.ScheduleLocalDataSource
import com.calender.domain.model.Daily
import com.calender.domain.model.Result
import com.calender.domain.model.Schedule
import com.calender.domain.repository.CalenderRepository
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate
import javax.inject.Inject

class CalenderRepositoryImpl @Inject constructor(
    private val scheduleLocalData : ScheduleLocalDataSource
    ): CalenderRepository {



    override fun getSearchSchedule(date : LocalDate): Flow<Result<List<Schedule>>> {
        return scheduleLocalData.getSearchSchedule(date)
    }

}
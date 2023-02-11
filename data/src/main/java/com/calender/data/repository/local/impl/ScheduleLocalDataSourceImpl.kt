package com.calender.data.repository.local.impl

import com.calender.data.database.dao.ScheduleDao
import com.calender.data.mapper.mapperToRecord
import com.calender.data.mapper.mapperToSchedule
import com.calender.data.model.local.ScheduleLocal
import com.calender.data.repository.local.interfaces.ScheduleLocalDataSource
import com.calender.domain.model.Result
import com.calender.domain.model.Schedule
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.time.LocalDate
import javax.inject.Inject
import javax.inject.Singleton


class ScheduleLocalDataSourceImpl @Inject constructor(
    private val scheduleDao: ScheduleDao) : ScheduleLocalDataSource
{
    override fun getSearchSchedule(date: LocalDate): Flow<Result<List<Schedule>>> = flow<Result<List<Schedule>>>{
        emit(Result.Loading)
        val schedule = scheduleDao.getSearchSchedule(date)
        if(schedule.isEmpty()){
            emit(Result.Empty)
        }else{
            emit(Result.Success(mapperToSchedule(schedule)))
        }
    }
}
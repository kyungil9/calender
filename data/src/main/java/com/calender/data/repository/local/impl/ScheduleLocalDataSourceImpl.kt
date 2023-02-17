package com.calender.data.repository.local.impl

import com.calender.data.database.dao.ScheduleDao
import com.calender.data.mapper.mapperToSchedule
import com.calender.data.mapper.mapperToScheduleList
import com.calender.data.repository.local.interfaces.ScheduleLocalDataSource
import com.calender.domain.model.Calender
import com.calender.domain.model.Daily
import com.calender.domain.model.Result
import com.calender.domain.model.Schedule
import kotlinx.coroutines.flow.*
import java.time.LocalDate
import javax.inject.Inject


class ScheduleLocalDataSourceImpl @Inject constructor(
    private val scheduleDao: ScheduleDao) : ScheduleLocalDataSource
{
    override fun getSearchSchedule(date: LocalDate): Flow<Result<List<Schedule>>> = flow<Result<List<Schedule>>>{
        emit(Result.Loading)
        val schedule = scheduleDao.getSearchSchedule(date)
        if(schedule.isEmpty()){
            emit(Result.Empty)
        }else{
            emit(Result.Success(mapperToScheduleList(schedule)))
        }
    }

    override fun getMonthSchedule(startDate: LocalDate, endDate: LocalDate): Flow<Result<Calender>> = channelFlow {
        scheduleDao.getMonthSchedule(startDate, endDate).collectLatest { schedule ->
            if (schedule.isEmpty())
                send(Result.Empty)
            else {
                val month = ArrayList<Daily>()
                var i =0
                month.add(Daily(date = schedule[0].date, list = arrayListOf(mapperToSchedule(schedule[0]))))
                for (list in schedule){
                    if (schedule.indexOf(list) == 0)
                        continue
                    if (month[i].date == list.date)
                        month[i].list.add(mapperToSchedule(list))
                    else{
                        i++
                        month.add(Daily(date = list.date, list = arrayListOf(mapperToSchedule(list))))
                    }
                }
                send(Result.Success(Calender(month = startDate.plusDays(10), list = month)))
            }
        }
    }.catch { e->
        emit(Result.Error(e))
    }
}
package com.calender.data.repository.local.interfaces

import com.calender.data.model.local.ScheduleLocal
import com.calender.domain.model.Calender
import com.calender.domain.model.Daily
import com.calender.domain.model.Result
import com.calender.domain.model.Schedule
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

interface ScheduleLocalDataSource {
    fun getSearchSchedule(date : LocalDate) : Flow<Result<List<Schedule>>>

    fun getMonthSchedule(startDate : LocalDate,endDate: LocalDate) : Flow<Result<Calender>>
}
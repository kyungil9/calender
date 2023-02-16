package com.calender.domain.repository


import com.calender.domain.model.Calender
import com.calender.domain.model.Daily
import com.calender.domain.model.Result
import com.calender.domain.model.Schedule
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

interface CalenderRepository {
    fun getSearchSchedule(date : LocalDate): Flow<Result<List<Schedule>>>

    fun getMonthSchedule(startDate : LocalDate,endDate: LocalDate) : Flow<Result<Calender>>
}
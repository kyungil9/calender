package com.calender.domain.usecase.calender

import com.calender.domain.model.Calender
import com.calender.domain.model.Daily
import com.calender.domain.model.Result
import com.calender.domain.repository.CalenderRepository
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate
import javax.inject.Inject

class GetMonthScheduleUseCase @Inject constructor(
    private val calenderRepository: CalenderRepository
) {
    operator fun invoke(startDate : LocalDate , endDate: LocalDate) : Flow<Result<Calender>>{
        return calenderRepository.getMonthSchedule(startDate, endDate)
    }
}
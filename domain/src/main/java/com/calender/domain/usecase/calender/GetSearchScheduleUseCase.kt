package com.calender.domain.usecase.calender

import com.calender.domain.model.Result
import com.calender.domain.model.Schedule
import com.calender.domain.repository.CalenderRepository
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate
import javax.inject.Inject

class GetSearchScheduleUseCase @Inject constructor(private val calenderRepository: CalenderRepository) {
    operator fun invoke(date : LocalDate) : Flow<Result<List<Schedule>>> {//model을 convert하는거 추가
        return calenderRepository.getSearchSchedule(date)
    }

}
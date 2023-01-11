package com.calender.domain.usecase

import com.calender.domain.model.Schedule
import com.calender.domain.repository.CalenderRepository
import java.time.LocalDate
import javax.inject.Inject

class GetSearchScheduleUseCase @Inject constructor(private val calenderRepository: CalenderRepository) {
    operator fun invoke(date : LocalDate) : List<Schedule> {//model을 convert하는거 추가
        return calenderRepository.getSearchSchedule(date)
    }

}
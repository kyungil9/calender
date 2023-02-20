package com.calender.domain.usecase.calender

import com.calender.domain.model.Schedule
import com.calender.domain.repository.CalenderRepository
import javax.inject.Inject

class InsertScheduleUseCase @Inject constructor(
    private val calenderRepository: CalenderRepository
) {
    operator fun invoke(schedule: Schedule){
        calenderRepository.insertSchedule(schedule)
    }
}
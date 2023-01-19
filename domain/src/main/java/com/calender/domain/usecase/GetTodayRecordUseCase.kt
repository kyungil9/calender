package com.calender.domain.usecase

import com.calender.domain.model.Record
import com.calender.domain.repository.RecordRepository
import java.time.LocalDate
import javax.inject.Inject

class GetTodayRecordUseCase @Inject constructor(
    private val recordRepository: RecordRepository
) {
    operator fun invoke(date: LocalDate) : List<Record>{
        return recordRepository.getTodayRecord(date)
    }
}
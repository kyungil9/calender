package com.calender.domain.usecase.record

import com.calender.domain.model.Record
import com.calender.domain.model.Result
import com.calender.domain.repository.RecordRepository
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate
import java.time.LocalDateTime
import javax.inject.Inject

class GetTodayRecordUseCase @Inject constructor(
    private val recordRepository: RecordRepository
) {
    operator fun invoke(date: LocalDateTime) : Flow<Result<List<Record>>> {
        return recordRepository.getTodayRecord(date)
    }
}
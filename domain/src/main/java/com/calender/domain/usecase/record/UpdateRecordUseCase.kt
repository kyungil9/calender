package com.calender.domain.usecase.record

import com.calender.domain.repository.RecordRepository
import java.time.LocalDateTime
import javax.inject.Inject

class UpdateRecordUseCase @Inject constructor(
    private val recordRepository: RecordRepository
) {
    operator fun invoke(endTime : LocalDateTime, progressTime : Long , id :Int){
        recordRepository.updateRecord(endTime,progressTime, id)
    }
}
package com.calender.domain.usecase.record

import com.calender.domain.model.Record
import com.calender.domain.repository.RecordRepository
import javax.inject.Inject

class InsertRecordUseCase @Inject constructor(
    private val recordRepository: RecordRepository
) {
    operator fun invoke(record : Record){
        recordRepository.insertRecord(record)
    }
}
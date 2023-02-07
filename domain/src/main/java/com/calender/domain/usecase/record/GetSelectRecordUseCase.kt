package com.calender.domain.usecase.record

import com.calender.domain.model.Record
import com.calender.domain.model.Result
import com.calender.domain.repository.RecordRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSelectRecordUseCase @Inject constructor(
    private val recordRepository: RecordRepository
) {
    operator fun invoke(): Flow<Result<Record>>{
        return recordRepository.getSelectRecord()
    }
}
package com.calender.domain.repository


import com.calender.domain.model.Record
import com.calender.domain.model.Result
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

interface RecordRepository {
    fun getTodayRecord(date : LocalDate) : Flow<Result<List<Record>>>
}
package com.calender.data.repository.local.interfaces

import com.calender.data.model.local.RecordLocal
import com.calender.domain.model.Record
import com.calender.domain.model.Result
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

interface RecordLocalDataSource {
    fun getTodayRecord(date : LocalDate): Flow<Result<List<Record>>>
}
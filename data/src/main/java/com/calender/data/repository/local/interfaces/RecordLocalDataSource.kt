package com.calender.data.repository.local.interfaces

import com.calender.data.model.local.RecordLocal
import com.calender.domain.model.Record
import com.calender.domain.model.Result
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate
import java.time.LocalDateTime

interface RecordLocalDataSource {
    fun getTodayRecord(date : LocalDateTime): Flow<Result<List<Record>>>

    fun insertRecord(record : RecordLocal)

    fun updateRecord(endTime : LocalDateTime,progressTime : Long,id:Int)

    fun getSelectRecord(): Flow<Result<Record>>
}
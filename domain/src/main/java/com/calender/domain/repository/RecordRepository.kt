package com.calender.domain.repository


import com.calender.domain.model.Record
import com.calender.domain.model.Result
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate
import java.time.LocalDateTime

interface RecordRepository {
    fun getTodayRecord(date : LocalDateTime) : Flow<Result<List<Record>>>

    fun getSelectRecord() : Flow<Result<Record>>

    fun insertRecord(record: Record)

    fun updateRecord(endTime: LocalDateTime,progressTime : Long, id : Int)
}
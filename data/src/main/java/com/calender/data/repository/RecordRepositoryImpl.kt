package com.calender.data.repository

import com.calender.data.mapper.mapperToRecord
import com.calender.data.mapper.mapperToRecordLocal
import com.calender.data.repository.local.interfaces.RecordLocalDataSource
import com.calender.domain.model.Record
import com.calender.domain.model.Result
import com.calender.domain.repository.RecordRepository
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate
import java.time.LocalDateTime
import javax.inject.Inject
import javax.inject.Singleton


class RecordRepositoryImpl @Inject constructor(
    private val recordLocalDataSource: RecordLocalDataSource
) : RecordRepository{
    override fun getTodayRecord(date: LocalDateTime): Flow<Result<List<Record>>> {
        return recordLocalDataSource.getTodayRecord(date)
    }

    override fun insertRecord(record: Record) {
        recordLocalDataSource.insertRecord(mapperToRecordLocal(record))
    }

    override fun updateRecord(endTime: LocalDateTime, progressTime: Long, id: Int) {
        recordLocalDataSource.updateRecord(endTime,progressTime,id)
    }

    override fun getSelectRecord(): Flow<Result<Record>> {
        return recordLocalDataSource.getSelectRecord()
    }
}
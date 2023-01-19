package com.calender.data.repository

import com.calender.data.mapper.mapperToRecord
import com.calender.data.repository.local.interfaces.RecordLocalDataSource
import com.calender.domain.model.Record
import com.calender.domain.repository.RecordRepository
import java.time.LocalDate
import javax.inject.Inject

class RecordRepositoryImpl @Inject constructor(
    private val recordLocalDataSource: RecordLocalDataSource
) : RecordRepository{
    override fun getTodayRecord(date: LocalDate): List<Record> {
        return mapperToRecord(recordLocalDataSource.getTodayRecord(date))
    }
}
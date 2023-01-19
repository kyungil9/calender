package com.calender.data.repository.local.impl

import com.calender.data.database.dao.RecordDao
import com.calender.data.mapper.mapperToRecord
import com.calender.data.model.local.RecordLocal
import com.calender.data.repository.local.interfaces.RecordLocalDataSource
import com.calender.domain.model.Record
import java.time.LocalDate
import javax.inject.Inject

class RecordLocalDataSourceImpl @Inject constructor(
    private val recordDao: RecordDao
) : RecordLocalDataSource{
    override fun getTodayRecord(date: LocalDate): List<RecordLocal> {
        return recordDao.getTodayRecordInfo(date)
    }
}
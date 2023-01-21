package com.calender.data.repository.local.impl

import com.calender.data.database.dao.RecordDao
import com.calender.data.mapper.mapperToRecord
import com.calender.data.model.local.RecordLocal
import com.calender.data.repository.local.interfaces.RecordLocalDataSource
import com.calender.domain.model.Record
import com.calender.domain.model.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.time.LocalDate
import javax.inject.Inject

class RecordLocalDataSourceImpl @Inject constructor(
    private val recordDao: RecordDao
) : RecordLocalDataSource{
    override fun getTodayRecord(date: LocalDate): Flow<Result<List<Record>>> = flow<Result<List<Record>>> {
        emit(Result.Loading)
        val record = recordDao.getTodayRecordInfo(date)
        if(record.isEmpty()){
            emit(Result.Empty)
        }else{
            emit(Result.Success(mapperToRecord(record)))
        }
    }
}
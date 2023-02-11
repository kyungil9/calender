package com.calender.data.repository.local.impl

import com.calender.data.database.dao.RecordDao
import com.calender.data.mapper.mapperToDateTimetoLong
import com.calender.data.mapper.mapperToRecord
import com.calender.data.mapper.mapperToRecordSingle
import com.calender.data.model.local.RecordLocal
import com.calender.data.repository.local.interfaces.RecordLocalDataSource
import com.calender.domain.model.Record
import com.calender.domain.model.Result
import kotlinx.coroutines.flow.*
import java.time.LocalDate
import java.time.LocalDateTime
import javax.inject.Inject
import javax.inject.Singleton


class RecordLocalDataSourceImpl @Inject constructor(
    private val recordDao: RecordDao
) : RecordLocalDataSource{
    override fun getTodayRecord(date: LocalDateTime): Flow<Result<List<Record>>> = channelFlow {
        recordDao.getTodayRecordInfo(mapperToDateTimetoLong(date)).collectLatest { record ->
            if(record.isEmpty()){
                send(Result.Empty)
            }else{
                send(Result.Success(mapperToRecord(record)))
            }
        }
    }.catch { e->
        emit(Result.Error(e))
    }

    override fun getSelectRecord(): Flow<Result<Record>> = channelFlow {
        recordDao.getSelectRecordInfo(true).collectLatest { record ->
            if (record.isEmpty()){
                send(Result.Empty)
            }else{
                send(Result.Success(mapperToRecordSingle(record[0])))
            }
        }
    }.catch { e->
        emit(Result.Error(e))
    }

    override fun insertRecord(record: RecordLocal) {
        recordDao.insertRecord(record)
    }

    override fun updateRecord(endTime: LocalDateTime, progressTime: Long, id: Int) {
        recordDao.updateRecord(endTime,progressTime,id,false)
    }
}
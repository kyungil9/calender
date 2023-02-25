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
import java.time.Duration
import java.time.LocalDate
import java.time.LocalDateTime
import javax.inject.Inject
import javax.inject.Singleton


class RecordLocalDataSourceImpl @Inject constructor(
    private val recordDao: RecordDao
) : RecordLocalDataSource{
    override fun getTodayRecord(date: LocalDateTime): Flow<Result<List<Record>>> = channelFlow {
        val startDateTime = LocalDateTime.of(date.year,date.monthValue,date.dayOfMonth,0,0,0)
        val endDateTime = LocalDateTime.of(date.year,date.monthValue,date.dayOfMonth,23,59,59)
        recordDao.getTodayRecordInfo(mapperToDateTimetoLong(startDateTime),
            mapperToDateTimetoLong(endDateTime)
        ).collectLatest { record ->
            if(record.isEmpty()){
                send(Result.Empty)
            }else{
                val recordList = arrayListOf<Record>()
                for (item in record){
                    if (item.endTime == null){
                        item.endTime = endDateTime
                        if (item.startTime < startDateTime)
                            item.progressTime = Duration.between(startDateTime,item.endTime).toMinutes()
                        else
                            item.progressTime = Duration.between(item.startTime,item.endTime).toMinutes()
                    }else{
                        if (item.startTime < startDateTime && item.endTime!! > endDateTime){
                            item.startTime = startDateTime
                            item.endTime = endDateTime
                            item.progressTime = Duration.between(item.startTime,item.endTime).toMinutes()
                        }else if (item.endTime!! > endDateTime){
                            item.endTime = endDateTime
                            item.progressTime = Duration.between(item.startTime,item.endTime).toMinutes()
                        }else if (item.startTime < startDateTime)
                            item.progressTime = Duration.between(startDateTime,item.endTime).toMinutes()
                    }
                    recordList.add(mapperToRecord(item))
                }
                send(Result.Success(recordList))
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
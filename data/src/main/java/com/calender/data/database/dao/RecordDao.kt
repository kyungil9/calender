package com.calender.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.calender.data.model.local.RecordLocal
import com.calender.domain.model.Result
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate
import java.time.LocalDateTime

@Dao
interface RecordDao {
    @Query("select * from record")
    fun getAllRecordInfo(): Flow<List<RecordLocal>>

    @Query("select * from record where startTime >= :date or endTime < :date")
    fun getTodayRecordInfo(date : Long):Flow<List<RecordLocal>>

    @Insert
    fun insertRecord(record : RecordLocal)

    @Query("update record set endTime = :mEndTime,progressTime = :mProgressTime, select_check = :check WHERE id = :id")
    fun updateRecord(mEndTime: LocalDateTime,mProgressTime : Long,id : Int, check : Boolean)

    @Query("select * from record where select_check = :check")
    fun getSelectRecordInfo(check: Boolean):Flow<List<RecordLocal>>
}
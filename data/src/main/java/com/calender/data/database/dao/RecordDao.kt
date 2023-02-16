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

    @Query("select * from record where (startTime >= :startDateTime and endTime < :endDateTime)")//추후 수정 필요??,하루를 넘기는 데이터를 어찌 처리할지
    fun getTodayRecordInfo(startDateTime: Long ,endDateTime: Long):Flow<List<RecordLocal>>

    @Insert
    fun insertRecord(record : RecordLocal)

    @Query("update record set endTime = :mEndTime,progressTime = :mProgressTime, select_check = :check WHERE id = :id")
    fun updateRecord(mEndTime: LocalDateTime,mProgressTime : Long,id : Int, check : Boolean)

    @Query("select * from record where select_check = :check")
    fun getSelectRecordInfo(check: Boolean):Flow<List<RecordLocal>>
}
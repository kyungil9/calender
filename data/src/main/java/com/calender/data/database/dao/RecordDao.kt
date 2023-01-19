package com.calender.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.calender.data.model.local.RecordLocal
import java.time.LocalDate

@Dao
interface RecordDao {
    @Query("select * from record")
    fun getAllRecordInfo():List<RecordLocal>

    @Query("select * from record where startTime > :date ")//수정필요
    fun getTodayRecordInfo(date : LocalDate):List<RecordLocal>
}
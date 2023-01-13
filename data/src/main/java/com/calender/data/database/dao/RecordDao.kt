package com.calender.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.calender.data.model.local.RecordLocal

@Dao
interface RecordDao {
    @Query("select * from record")
    fun getAllRecordInfo():List<RecordLocal>
}
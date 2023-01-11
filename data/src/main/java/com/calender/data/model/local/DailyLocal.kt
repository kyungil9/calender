package com.calender.data.model.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity(tableName = "daily")
data class DailyLocal(
    @PrimaryKey val date : LocalDate = LocalDate.now(),
    var record : String
)

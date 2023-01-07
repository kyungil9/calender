package com.calender.main.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity(tableName = "daily")
data class Daily(
    @PrimaryKey val date : LocalDate = LocalDate.now(),
    var record : String
)

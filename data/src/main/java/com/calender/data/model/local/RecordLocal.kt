package com.calender.data.model.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity(tableName = "record")
data class RecordLocal(
    @PrimaryKey(autoGenerate = true) val id : Int,
    val tag : String,
    var startTime : LocalDateTime,
    var endTime: LocalDateTime,
    var progressTime : Int,
    var check : Boolean
)
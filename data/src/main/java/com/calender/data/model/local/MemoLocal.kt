package com.calender.data.model.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "memo")
data class MemoLocal(
    @PrimaryKey(autoGenerate = true) val id : Int,
    var title : String,
    var detail : String,
    var tag : String
)

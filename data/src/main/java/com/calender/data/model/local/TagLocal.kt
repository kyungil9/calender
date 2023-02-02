package com.calender.data.model.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tag")
data class TagLocal(
    @PrimaryKey(autoGenerate = true) val id : Int = 0,
    var tag :String
)
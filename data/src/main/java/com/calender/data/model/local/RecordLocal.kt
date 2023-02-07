package com.calender.data.model.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity(tableName = "record")
data class RecordLocal(
    @PrimaryKey(autoGenerate = true) val id : Int =0,
    val tag : String,
    var startTime : LocalDateTime,
    var endTime: LocalDateTime?,
    var progressTime : Long,
    @ColumnInfo(name = "select_check") var check : Boolean
)

//foreignKeys = [
//ForeignKey(
//entity = TagLocal::class,
//parentColumns = ["tag"],
//childColumns = ["tag"],
//onDelete = ForeignKey.NO_ACTION
//)
//]
package com.calender.data.model.local

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity(tableName = "todo")
data class ToDoCheckLocal(
    @PrimaryKey(autoGenerate = true) val id : Int = 0,
    var date : LocalDate = LocalDate.now(),
    var doIt : String,
    var tag : String,
    var repeat : Int,
    var state : Int, //0 시작전, 1 진행중, 2 완료
    var statePercent : Int,
    var endDate : LocalDate,
    var alarm : Int, // 알람 시간대 번호로 설정
)

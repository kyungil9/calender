package com.calender.data.model.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity(tableName = "todo")
data class ToDoCheckLocal(
    @PrimaryKey(autoGenerate = true) val id : Int,
    var date : LocalDate = LocalDate.now(),
    var doIt : String,
    var tag : String,
    var repeat : Int,
    var state : Int, //0 시작전, 1 진행중, 2 완료
    var statePercent : Int,
    var period : Int, //0 오늘, 1 내일, 2 일주일, 3 원하는시간
    var endDate : LocalDate,
    var alarm : Int, // 알람 시간대 번호로 설정
)

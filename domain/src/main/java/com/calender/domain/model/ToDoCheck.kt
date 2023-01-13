package com.calender.domain.model

import java.time.LocalDate

data class ToDoCheck(
    var date : LocalDate,
    var doIt : String,
    var tag : String,
    var repeat : Int,
    var state : Int, //0 시작전, 1 진행중, 2 완료
    var statePercent : Int,
    var period : Int, //0 오늘, 1 내일, 2 일주일, 3 원하는시간
    var endDate : LocalDate,
    var alarm : Int, // 알람 시간대 번호로 설정
)

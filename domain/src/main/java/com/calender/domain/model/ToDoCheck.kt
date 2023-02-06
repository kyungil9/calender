package com.calender.domain.model

import java.time.LocalDate

data class ToDoCheck(
    val id : Int = 0,
    var date : LocalDate = LocalDate.now(),
    var doIt : String = "",
    var tag : String = "",
    var repeat : Int =0, //0 반복x 1매일 2매주 3매년
    var state : Int=0, //0 시작전, 1 진행중, 2 완료
    var statePercent : Int = 0, //5단위로 0~100
    var endDate : LocalDate = LocalDate.now(),
    var alarm : Int = 0, // 알람 시간대 번호로 설정
)

enum class ToDoCheckMode{
    STATE,
    STATEPERCENT
}
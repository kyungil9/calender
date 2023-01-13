package com.calender.domain.model

import java.time.LocalDate
import java.time.LocalTime

data class Schedule(
    var date: LocalDate,
    var timeSelect : Boolean, // false : 종일 , true : 시간대 선택
    var startTime : LocalTime,
    var endTime : LocalTime,
    var repeat : Int,//0 :x , 1,2,3,4(매일,매주,매월,매년)
    var tag : String, // 캘린더 태그설정
    var alarm : Int, //알람 시간대 번호로 설정
    var color : Int,
    var memoryDay : Boolean,
    var detail : String //내용
)
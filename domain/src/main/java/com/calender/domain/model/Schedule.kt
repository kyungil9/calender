package com.calender.domain.model

import java.time.LocalDate
import java.time.LocalTime

data class Schedule(
    val id : Int = 0,
    var date: LocalDate = LocalDate.now(),
    var timeSelect : Boolean = false, // false : 종일 , true : 시간대 선택
    var startTime : LocalTime = LocalTime.of(0,0,0),
    var endTime : LocalTime = LocalTime.of(0,0,0),
    var repeat : Int = 0,//0 :x , 1,2,3,4(매일,매주,매월,매년)
    var tag : String = "basic", // 캘린더 태그설정??
    var alarm : Int = 0, //알람 시간대 번호로 설정??
    var color : Int = 0,
    var memoryDay : Boolean = false,
    var detail : String = ""//내용
)
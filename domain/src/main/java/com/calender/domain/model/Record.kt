package com.calender.domain.model

import java.time.LocalDateTime

data class Record(
    val id : Int,
    val tag : String,
    var startTime : LocalDateTime,
    var endTime: LocalDateTime?,
    var progressTime : Long,
    var check : Boolean
)

package com.calender.domain.model

import java.time.LocalDateTime

data class Record(
    val tag : String,
    var startTime : LocalDateTime,
    var endTime: LocalDateTime,
    var progressTime : Int,
    var check : Boolean
)

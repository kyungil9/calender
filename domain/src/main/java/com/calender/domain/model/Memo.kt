package com.calender.domain.model

import java.io.Serializable

data class Memo(
    val id :Int,
    var title : String,
    var detail : String,
    var tag : String
) : Serializable
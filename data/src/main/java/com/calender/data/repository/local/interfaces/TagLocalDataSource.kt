package com.calender.data.repository.local.interfaces

import com.calender.data.model.local.TagLocal
import com.calender.domain.model.Result
import kotlinx.coroutines.flow.Flow

interface TagLocalDataSource {
    fun getToDoAllTag() : Flow<Result<List<String>>>
    fun getRecordAllTag() : Flow<Result<List<String>>>
    fun getToDoOneTag() : String
    fun insertTag(tag : TagLocal)
    fun delectTag(tag : String,mode : Int)
}
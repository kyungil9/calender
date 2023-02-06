package com.calender.domain.repository

import com.calender.domain.model.Result
import kotlinx.coroutines.flow.Flow

interface TagRepository {
    fun insertTag(tag : String,mode: Int)

    fun getToDoOneTag() : String

    fun getToDoAllTag() : Flow<Result<List<String>>>

    fun getRecordAllTag() : Flow<Result<List<String>>>
    fun deleteTag(tag :String,mode:Int)
}
package com.calender.domain.repository

import com.calender.domain.model.Result
import kotlinx.coroutines.flow.Flow

interface TagRepository {
    fun insertTag(tag : String)

    fun getOneTag() : String

    fun getAllTag() : Flow<Result<List<String>>>

    fun deleteTag(tag :String)
}
package com.calender.data.repository.local.interfaces

import com.calender.data.model.local.TagLocal
import com.calender.domain.model.Result
import kotlinx.coroutines.flow.Flow

interface TagLocalDataSource {
    fun getAllTag() : Flow<Result<List<String>>>

    fun insertTag(tag : TagLocal)
}
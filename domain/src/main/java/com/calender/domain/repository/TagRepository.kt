package com.calender.domain.repository

import kotlinx.coroutines.flow.Flow

interface TagRepository {
    fun insertTag(tag : String)

    fun getAllTag() : Flow<Result<List<String>>>
}
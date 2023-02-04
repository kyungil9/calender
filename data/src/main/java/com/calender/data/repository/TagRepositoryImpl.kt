package com.calender.data.repository

import com.calender.data.mapper.mapperToTagLocal
import com.calender.data.repository.local.interfaces.TagLocalDataSource
import com.calender.domain.model.Result
import com.calender.domain.repository.TagRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TagRepositoryImpl @Inject constructor(
    private val tagLocalData : TagLocalDataSource
) : TagRepository{

    override fun getAllTag(): Flow<Result<List<String>>> {
        return tagLocalData.getAllTag()
    }

    override fun getOneTag(): String {
        return tagLocalData.getOneTag()
    }
    override fun insertTag(tag: String) {
        tagLocalData.insertTag(mapperToTagLocal(tag))
    }

    override fun deleteTag(tag: String) {
        tagLocalData.delectTag(tag)
    }
}
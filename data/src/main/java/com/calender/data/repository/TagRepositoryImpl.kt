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

    override fun getToDoAllTag(): Flow<Result<List<String>>> {
        return tagLocalData.getToDoAllTag()
    }

    override fun getRecordAllTag(): Flow<Result<List<String>>> {
        return tagLocalData.getRecordAllTag()
    }

    override fun getToDoOneTag(): String {
        return tagLocalData.getToDoOneTag()
    }
    override fun insertTag(tag: String,mode: Int) {
        tagLocalData.insertTag(mapperToTagLocal(tag,mode))
    }

    override fun deleteTag(tag: String,mode : Int) {
        tagLocalData.delectTag(tag,mode)
    }
}
package com.calender.data.repository.local.impl

import com.calender.data.database.dao.TagDao
import com.calender.data.mapper.mapperToTag
import com.calender.data.model.local.TagLocal
import com.calender.data.repository.local.interfaces.TagLocalDataSource
import kotlinx.coroutines.flow.Flow
import com.calender.domain.model.Result
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

class TagLocalDataSourceImpl @Inject constructor(
    private val tagDao: TagDao
) : TagLocalDataSource{
    override fun getAllTag(): Flow<Result<List<String>>> = channelFlow<Result<List<String>>> {
        tagDao.getAllTag().collectLatest { tag ->
            if (tag.isEmpty())
                send(Result.Empty)
            else{
                val tagList = mapperToTag(tag)
                send(Result.Success(tagList))
            }
        }
    }.catch { e->
        emit(Result.Error(e))
    }

    override fun insertTag(tag: TagLocal) {
        tagDao.insertTag(tag)
    }
}
package com.calender.data.repository

import com.calender.data.mapper.mapperToMemo
import com.calender.data.model.local.MemoLocal
import com.calender.data.repository.local.interfaces.MemoLocalDataSource
import com.calender.domain.model.Memo
import com.calender.domain.model.Result
import com.calender.domain.repository.MemoRepositoy
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton


class MemoRepositoryImpl @Inject constructor(
    private val memoLocalDataSource: MemoLocalDataSource
) : MemoRepositoy{
    override fun getAllMemo(): Flow<Result<List<Memo>>> {
        return memoLocalDataSource.getAllMemo()
    }
}
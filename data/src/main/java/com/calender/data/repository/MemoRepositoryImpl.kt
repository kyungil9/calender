package com.calender.data.repository

import com.calender.data.mapper.mapperToMemo
import com.calender.data.repository.local.interfaces.MemoLocalDataSource
import com.calender.domain.model.Memo
import com.calender.domain.repository.MemoRepositoy
import javax.inject.Inject

class MemoRepositoryImpl @Inject constructor(
    private val memoLocalDataSource: MemoLocalDataSource
) : MemoRepositoy{
    override fun getAllMemo(): List<Memo> {
        return mapperToMemo(memoLocalDataSource.getAllMemo())
    }
}
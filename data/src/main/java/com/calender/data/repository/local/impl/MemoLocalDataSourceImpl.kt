package com.calender.data.repository.local.impl

import com.calender.data.database.dao.MemoDao
import com.calender.data.mapper.mapperToMemo
import com.calender.data.model.local.MemoLocal
import com.calender.data.repository.local.interfaces.MemoLocalDataSource
import com.calender.domain.model.Memo
import javax.inject.Inject

class MemoLocalDataSourceImpl @Inject constructor(
    private val memoDao: MemoDao
) : MemoLocalDataSource{
    override fun getAllMemo(): List<MemoLocal> {
        return memoDao.getAllMemoInfo()
    }
}
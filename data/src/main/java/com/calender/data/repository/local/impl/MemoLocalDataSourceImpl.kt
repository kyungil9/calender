package com.calender.data.repository.local.impl

import com.calender.data.database.dao.MemoDao
import com.calender.data.mapper.mapperToMemo
import com.calender.data.mapper.mapperToMemoLocal
import com.calender.data.model.local.MemoLocal
import com.calender.data.repository.local.interfaces.MemoLocalDataSource
import com.calender.domain.model.Memo
import com.calender.domain.model.Result
import kotlinx.coroutines.flow.*
import javax.inject.Inject
import javax.inject.Singleton


class MemoLocalDataSourceImpl @Inject constructor(
    private val memoDao: MemoDao
) : MemoLocalDataSource{
    override fun getAllMemo(): Flow<Result<List<Memo>>> = channelFlow{
        memoDao.getAllMemoInfo().collectLatest { memo ->
            if (memo.isEmpty())
                send(Result.Empty)
            else
                send(Result.Success(mapperToMemo(memo)))
        }
    }.catch { e->
        emit(Result.Error(e))
    }

    override fun insertMemo(memo: Memo) {
        memoDao.insertMemo(mapperToMemoLocal(memo))
    }

    override fun updateMemo(memo: Memo) {
        memoDao.updateMemo(mapperToMemoLocal(memo))
    }

    override fun delectMemo(memo: Memo) {
        memoDao.delectMemo(mapperToMemoLocal(memo))
    }
}
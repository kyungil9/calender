package com.calender.data.repository.local.impl

import com.calender.data.database.dao.MemoDao
import com.calender.data.mapper.mapperToMemo
import com.calender.data.model.local.MemoLocal
import com.calender.data.repository.local.interfaces.MemoLocalDataSource
import com.calender.domain.model.Memo
import com.calender.domain.model.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton


class MemoLocalDataSourceImpl @Inject constructor(
    private val memoDao: MemoDao
) : MemoLocalDataSource{
    override fun getAllMemo(): Flow<Result<List<Memo>>> = flow<Result<List<Memo>>>{
        emit(Result.Loading)
        val memo = memoDao.getAllMemoInfo()
        if(memo.isEmpty()){
            emit(Result.Empty)
        }else{
            emit(Result.Success(mapperToMemo(memo)))
        }
    }
}
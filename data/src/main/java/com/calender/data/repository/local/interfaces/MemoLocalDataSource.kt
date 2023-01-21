package com.calender.data.repository.local.interfaces

import com.calender.data.model.local.MemoLocal
import com.calender.domain.model.Memo
import com.calender.domain.model.Result
import kotlinx.coroutines.flow.Flow


interface MemoLocalDataSource {
    fun getAllMemo(): Flow<Result<List<Memo>>>
}
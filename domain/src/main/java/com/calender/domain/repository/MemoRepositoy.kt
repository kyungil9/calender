package com.calender.domain.repository

import com.calender.domain.model.Memo
import com.calender.domain.model.Result
import kotlinx.coroutines.flow.Flow

interface MemoRepositoy {
    fun getAllMemo(): Flow<Result<List<Memo>>>
}
package com.calender.domain.usecase

import com.calender.domain.model.Memo
import com.calender.domain.model.Result
import com.calender.domain.repository.MemoRepositoy
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllMemoUseCase @Inject constructor(
    private val memoRepositoy: MemoRepositoy
){
    operator fun invoke(): Flow<Result<List<Memo>>> {
        return memoRepositoy.getAllMemo()
    }
}
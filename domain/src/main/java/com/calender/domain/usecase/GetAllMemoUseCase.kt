package com.calender.domain.usecase

import com.calender.domain.model.Memo
import com.calender.domain.repository.MemoRepositoy
import javax.inject.Inject

class GetAllMemoUseCase @Inject constructor(
    private val memoRepositoy: MemoRepositoy
){
    operator fun invoke():List<Memo>{
        return memoRepositoy.getAllMemo()
    }
}
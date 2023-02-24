package com.calender.domain.usecase.memo

import com.calender.domain.model.Memo
import com.calender.domain.repository.MemoRepositoy
import javax.inject.Inject

class UpdateMemoUseCase @Inject constructor(
    private val memoRepositoy: MemoRepositoy
) {
    operator fun invoke(memo: Memo){
        memoRepositoy.updateMemo(memo)
    }
}
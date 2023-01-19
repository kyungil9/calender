package com.calender.domain.repository

import com.calender.domain.model.Memo

interface MemoRepositoy {
    fun getAllMemo():List<Memo>
}
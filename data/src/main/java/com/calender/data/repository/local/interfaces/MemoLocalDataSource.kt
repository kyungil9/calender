package com.calender.data.repository.local.interfaces

import com.calender.data.model.local.MemoLocal


interface MemoLocalDataSource {
    fun getAllMemo():List<MemoLocal>
}
package com.calender.data.repository.local.interfaces

import com.calender.data.model.local.DailyLocal

interface DailyLocalDataSource {
    fun getAllDailys() : List<DailyLocal>
}
package com.calender.data.repository.local.impl

import com.calender.data.database.dao.DailyDao
import com.calender.data.model.local.DailyLocal
import com.calender.data.repository.local.interfaces.DailyLocalDataSource
import javax.inject.Inject

class DailyLocalDataSourceImpl @Inject constructor(private val dailyDao : DailyDao) : DailyLocalDataSource {
    override fun getAllDailys(): List<DailyLocal> {
        return dailyDao.getAllDailyInfo()
    }
}
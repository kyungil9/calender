package com.calender.data.repository.local.interfaces

import com.calender.data.model.local.RecordLocal
import java.time.LocalDate

interface RecordLocalDataSource {
    fun getTodayRecord(date : LocalDate):List<RecordLocal>
}
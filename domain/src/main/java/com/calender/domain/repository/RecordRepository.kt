package com.calender.domain.repository


import com.calender.domain.model.Record
import java.time.LocalDate

interface RecordRepository {
    fun getTodayRecord(date : LocalDate):List<Record>
}
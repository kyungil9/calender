package com.calender.data.repository.local.interfaces

import com.calender.data.model.local.ScheduleLocal
import java.time.LocalDate

interface ScheduleLocalDataSource {
    fun getSearchSchedule(date : LocalDate) : List<ScheduleLocal>
}
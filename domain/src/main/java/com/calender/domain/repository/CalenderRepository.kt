package com.calender.domain.repository


import com.calender.domain.model.Daily
import com.calender.domain.model.Schedule
import java.time.LocalDate

interface CalenderRepository {

    fun getAllDailyData(): List<Daily>

    fun getSearchSchedule(date : LocalDate):List<Schedule>

}
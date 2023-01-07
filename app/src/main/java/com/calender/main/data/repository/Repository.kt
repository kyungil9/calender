package com.calender.main.data.repository

import com.calender.main.data.database.DailyDao
import javax.inject.Inject

class Repository @Inject constructor(private val dailyDao : DailyDao){

}
package com.calender.presentation.utils

import com.calender.domain.model.Daily
import com.calender.presentation.view.adapter.DayAdapter
import java.time.LocalDate

class Calender {
    var dailyList = ArrayList<Daily>()
    var currentDate : LocalDate? = null

    fun createHomeWeek():DayAdapter{
        dailyList.clear()
        var date = LocalDate.now()
        currentDate = date
        date = date.plusDays((-(date.dayOfWeek.value-1)).toLong())
        val tempMonth = date.monthValue
        val daily = Daily(date,"")
        dailyList.add(daily)
        for(i in 0..5) {
            date = date.plusDays(1)
            val daily = Daily(date,"")
            dailyList.add(daily)
        }
        return DayAdapter(tempMonth,7)
    }

    fun createMonth(position: Int):DayAdapter{
        dailyList.clear()
        var date = LocalDate.now()
        date = date.plusMonths((position).toLong())
        date = date.withDayOfMonth(1)
        currentDate = date
        val tempMonth = date.monthValue

        var size = 33
        if (date.monthValue == 2 && !date.isLeapYear && date.dayOfWeek.value == 7){//윤년이 없는 2월일 경우 4개가 나옴
            size = 26
        } else if (date.dayOfWeek.value == 5){
            when (date.monthValue){
                1,3,5,7,8,10,12-> size = 40
            }
        }else if (date.dayOfWeek.value == 6 && date.monthValue != 2)
            size = 40
        date = date.plusDays((-(date.dayOfWeek.value%7)).toLong())
        val daily = Daily(date,"")
        dailyList.add(daily)
        for(i in 0..size) {
            date = date.plusDays(1)
            val daily = Daily(date,"")
            dailyList.add(daily)
        }
        return DayAdapter(tempMonth,size+2)
    }
}
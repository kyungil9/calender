package com.calender.presentation.utils

import com.calender.domain.model.Daily
import com.calender.domain.model.Schedule
import com.calender.presentation.view.adapter.DayAdapter
import java.time.LocalDate
import java.time.Month

class CalenderUtils {
    var dailyList = ArrayList<Daily>()


    fun createHomeWeek():DayAdapter{
        dailyList.clear()
        var date = LocalDate.now()
        date = date.plusDays((-(date.dayOfWeek.value%7)).toLong())
        val tempMonth = date.monthValue
        val daily = Daily(date,ArrayList<Schedule>())
        dailyList.add(daily)
        for(i in 0..5) {
            date = date.plusDays(1)
            val daily = Daily(date,ArrayList<Schedule>())
            dailyList.add(daily)
        }
        return DayAdapter(tempMonth,7)
    }

    fun createMonth(position: Int):CalenderMonth{
        dailyList.clear()
        var date = LocalDate.now()
        date = date.plusMonths((position).toLong())
        date = date.withDayOfMonth(1)
        val tempMonth = date.monthValue

        var size = 33
        if (date.monthValue == 2 && !date.isLeapYear && date.dayOfWeek.value == 7){//윤년이 없는 2월일 경우 4개가 나옴
            size = 26
        } else if (date.dayOfWeek.value == 5){//1일 날짜로 해당 달에 개수 분별
            when (date.monthValue){
                1,3,5,7,8,10,12-> size = 40
            }
        }else if (date.dayOfWeek.value == 6 && date.monthValue != 2)
            size = 40
        date = date.minusDays((date.dayOfWeek.value%7).toLong())
        val daily = Daily(date,ArrayList<Schedule>())
        dailyList.add(daily)
        for(i in 0..size) {
            date = date.plusDays(1)
            val daily = Daily(date,ArrayList<Schedule>())
            dailyList.add(daily)
        }
        return CalenderMonth(tempMonth,size+2)
    }

    companion object {
        fun transDayToKorean(date : Int):String{
            return when(date){
                1-> "월"
                2-> "화"
                3-> "수"
                4-> "목"
                5-> "금"
                6-> "토"
                7-> "일"
                else ->""
            }
        }
        fun getMonthPeriod(date: LocalDate) : MonthPeriod{
            var tempDate = date.withDayOfMonth(1)
            var size = 33
            if (date.monthValue == 2 && !date.isLeapYear && date.dayOfWeek.value == 7){//윤년이 없는 2월일 경우 4개가 나옴
                size = 26
            } else if (date.dayOfWeek.value == 5){//1일 날짜로 해당 달에 개수 분별
                when (date.monthValue){
                    1,3,5,7,8,10,12-> size = 40
                }
            }else if (date.dayOfWeek.value == 6 && date.monthValue != 2)
                size = 40
            val startDate = tempDate.minusDays((tempDate.dayOfWeek.value%7).toLong())
            val endDate = startDate.plusDays((size+1).toLong())
            return MonthPeriod(startDate, endDate)
        }
    }

}

data class CalenderMonth(
    val month : Int,
    val size : Int
)

data class MonthPeriod(
    val startDate: LocalDate,
    val endDate: LocalDate
)
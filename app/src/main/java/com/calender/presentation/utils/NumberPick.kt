package com.calender.presentation.utils

import java.time.LocalDate

class NumberPick {
    private val today = LocalDate.now()

    private val yearList = (2000..2050).toList()
    private val monthList = (1..12).toList()
    private var dayList = (1..31).toList()

    private val yearStrList = yearList.map { it.toString() }
    private val monthStrList = monthList.map { it.toString()+"월" }
    private var dayStrList = dayList.map { it.toString() }

    init {
        changeDayInfo(today)
    }

    fun setYear(year: Int):LocalDate{
        today.withYear(year+2000)
        return today
    }
    fun setMonth(month: Int):LocalDate{
        today.withMonth(month +1)
        return today
    }
    fun setDay(day: Int):LocalDate{
        today.withDayOfMonth(day+1)
        return today
    }

    fun getSelectDay():LocalDate{
        return today
    }

    fun getYearValue():Array<String>{
        return yearStrList.toTypedArray()
    }
    fun getMonthValue():Array<String>{
        return monthStrList.toTypedArray()
    }
    fun getDayValue():Array<String>{
        return dayStrList.toTypedArray()
    }

    fun getYearSize():Int{
        return yearStrList.size -1
    }
    fun getMonthSize():Int{
        return monthStrList.size -1
    }
    fun getDaySize():Int{
        return dayStrList.size -1
    }

    fun getYearCurrent():Int{
        return today.year -2000
    }
    fun getMonthCurrent():Int{
        return today.monthValue -1
    }
    fun getDayCurrent():Int{
        return today.dayOfMonth -1
    }

    fun changeDayInfo(today : LocalDate){
        when(today.monthValue){
            2 -> {
                if(today.isLeapYear)
                    dayList = (1..29).toList()
                else
                    dayList = (1..28).toList()
            }
            1,3,5,7,8,10,12 -> dayList = (1..31).toList()
            else -> dayList = (1..30).toList()
        }
        dayStrList = dayList.map { it.toString()+"일 " + Calender.transDayToKorean(today.withDayOfMonth(it).dayOfWeek.value) }
    }
}
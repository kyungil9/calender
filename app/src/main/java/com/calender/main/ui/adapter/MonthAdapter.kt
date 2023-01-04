package com.calender.main.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.calender.main.databinding.ListItemMonthBinding
import java.time.LocalDate

import java.util.*

class MonthAdapter : RecyclerView.Adapter<MonthAdapter.MonthView>() {
    val center = Int.MAX_VALUE / 2

    inner class MonthView(private val binding: ListItemMonthBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(position: Int){
            var date = LocalDate.now()
            date = date.plusMonths((position - center).toLong())
            date = date.withDayOfMonth(1)
            binding.itemMonthText.text = "${date.year}년 ${date.monthValue}월"
            val tempMonth = date.monthValue

            var dayList = mutableListOf<LocalDate>()
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
            dayList.add(date)
            for(i in 0..size) {
                date = date.plusDays(1)
                dayList.add(date)
            }

            val dayListManager = GridLayoutManager(binding.itemMonthDayList.context, 7)
            val dayListAdapter = DayAdapter(tempMonth, dayList)

            binding.itemMonthDayList.apply {
                layoutManager = dayListManager
                adapter = dayListAdapter
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MonthView {
        return MonthView(ListItemMonthBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: MonthView, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return Int.MAX_VALUE
    }
}
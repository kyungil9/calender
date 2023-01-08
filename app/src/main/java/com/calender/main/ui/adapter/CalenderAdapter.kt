package com.calender.main.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.calender.main.data.entity.Daily
import com.calender.main.databinding.ListCalenderBinding
import java.time.LocalDate



class CalenderAdapter :  RecyclerView.Adapter<CalenderAdapter.MonthView>() {
    val center = Int.MAX_VALUE / 2

    inner class MonthView(private val binding: ListCalenderBinding): RecyclerView.ViewHolder(binding.root){
        var dayListAdapter : DayAdapter? = null

        fun bind(position: Int){
            val dailyList = createCalender(position)
            val dayListManager = GridLayoutManager(binding.itemMonthDayList.context, 7)

            binding.itemMonthDayList.apply {
                layoutManager = dayListManager
                adapter = dayListAdapter
            }
            dayListAdapter?.submitList(dailyList) //데이터 삽입




        }
        fun createCalender(position: Int):ArrayList<Daily>{
            var dailyList = arrayListOf<Daily>()
            var date = LocalDate.now()
            date = date.plusMonths((position - center).toLong())
            date = date.withDayOfMonth(1)
            binding.itemMonthText.text = "${date.year}년 ${date.monthValue}월"
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
            dayListAdapter = DayAdapter(tempMonth,size+2)
            return dailyList
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MonthView {
        return MonthView(ListCalenderBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: MonthView, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return Int.MAX_VALUE
    }

}
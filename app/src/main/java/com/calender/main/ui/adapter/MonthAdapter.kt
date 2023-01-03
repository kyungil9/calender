package com.calender.main.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.calender.main.databinding.ListItemMonthBinding

import java.util.*

class MonthAdapter : RecyclerView.Adapter<MonthAdapter.MonthView>() {
    val center = Int.MAX_VALUE / 2
    private var calendar = Calendar.getInstance()

    inner class MonthView(private val binding: ListItemMonthBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(position: Int){
            calendar.time = Date()
            calendar.set(Calendar.DAY_OF_MONTH, 1)
            calendar.add(Calendar.MONTH, position - center)
            binding.itemMonthText.text = "${calendar.get(Calendar.YEAR)}년 ${calendar.get(Calendar.MONTH) + 1}월"
            val tempMonth = calendar.get(Calendar.MONTH)

            var dayList: MutableList<Date> = MutableList(6 * 7) { Date() }
            for(i in 0..5) {
                for(k in 0..6) {
                    calendar.add(Calendar.DAY_OF_MONTH, (1-calendar.get(Calendar.DAY_OF_WEEK)) + k)
                    dayList[i * 7 + k] = calendar.time
                }
                calendar.add(Calendar.WEEK_OF_MONTH, 1)
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
package com.calender.main.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.calender.main.data.viewmodels.Calender
import com.calender.main.databinding.ListCalenderBinding
import com.calender.main.ui.listener.RecyclerViewItemClickListener
import java.time.LocalDate



class CalenderAdapter : RecyclerView.Adapter<CalenderAdapter.MonthView>(),RecyclerViewItemClickListener {
    val center = Int.MAX_VALUE / 2
    private var listener:RecyclerViewItemClickListener ?= null
    inner class MonthView(private val binding: ListCalenderBinding): RecyclerView.ViewHolder(binding.root){
        var dayListAdapter : DayAdapter? = null
        var calender = Calender()
        fun bind(position: Int){
            dayListAdapter = calender.createMonth(position-center)
            var dailyList = calender.dailyList
            val dayListManager = GridLayoutManager(binding.itemMonthDayList.context, 7)

            binding.itemMonthDayList.apply {
                layoutManager = dayListManager
                adapter = dayListAdapter
            }
            dayListAdapter?.submitList(dailyList) //데이터 삽입
            dayListAdapter?.setOnItemClickListener(listener!!)
            binding.itemMonthText.text = "${calender.currentDate!!.year}년 ${calender.currentDate!!.monthValue}월"


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

    override fun onItemClickListener(date: LocalDate) {
        listener?.onItemClickListener(date)
    }
    fun setOnItemClickListener(listener : RecyclerViewItemClickListener){
        this.listener=listener
    }
}
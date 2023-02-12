package com.calender.presentation.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.calender.presentation.databinding.ListCalenderBinding
import com.calender.presentation.utils.Calender
import com.calender.presentation.listener.RecyclerViewItemClickListener
import java.time.LocalDate



class CalenderAdapter : RecyclerView.Adapter<CalenderAdapter.MonthView>(), RecyclerViewItemClickListener {
    val center = Int.MAX_VALUE / 2
    private var listener: RecyclerViewItemClickListener?= null
    var parentHeight = 0
    inner class MonthView(private val binding: ListCalenderBinding): RecyclerView.ViewHolder(binding.root){
        var dayListAdapter : DayAdapter? = null
        var calender = Calender()
        fun bind(position: Int){
            dayListAdapter = calender.createMonth(position-center)
            binding.apply {
                itemMonthDayList.apply {
                    adapter = dayListAdapter
                }
            }

            var dailyList = calender.dailyList
            dayListAdapter?.submitList(dailyList) //데이터 삽입
            dayListAdapter?.setOnItemClickListener(listener!!)
            dayListAdapter?.setcParentHeight(parentHeight)
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
    fun setcParentHeight(height : Int){
        parentHeight = height
    }
}
package com.calender.presentation.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.calender.presentation.databinding.ListCalenderBinding
import com.calender.presentation.utils.CalenderUtils
import com.calender.presentation.listener.RecyclerViewItemClickListener
import java.time.LocalDate



class CalenderAdapter : RecyclerView.Adapter<CalenderAdapter.MonthView>(), RecyclerViewItemClickListener {
    val center = Int.MAX_VALUE / 2
    private var listener: RecyclerViewItemClickListener?= null
    var parentHeight : LiveData<Int>? = null
    inner class MonthView(private val binding: ListCalenderBinding): RecyclerView.ViewHolder(binding.root){
        var dayListAdapter : DayAdapter? = null
        var calenderUtils = CalenderUtils()
        fun bind(position: Int){
            dayListAdapter = calenderUtils.createMonth(position-center)
            binding.apply {
                itemMonthDayList.apply {
                    adapter = dayListAdapter
                }
            }

            var dailyList = calenderUtils.dailyList
            dayListAdapter?.submitList(dailyList) //데이터 삽입
            dayListAdapter?.setOnItemClickListener(listener!!)
            dayListAdapter?.setcParentHeight(parentHeight!!)
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

    override fun onItemClickListener(date: LocalDate,view : View) {
        listener?.onItemClickListener(date,view)
    }
    fun setOnItemClickListener(listener : RecyclerViewItemClickListener){
        this.listener=listener
    }
    fun setcParentHeight(height : LiveData<Int>){
        parentHeight = height
    }
}
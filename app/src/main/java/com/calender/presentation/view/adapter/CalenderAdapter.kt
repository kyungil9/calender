package com.calender.presentation.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.calender.domain.model.Calender
import com.calender.domain.model.Daily
import com.calender.presentation.databinding.ListCalenderBinding
import com.calender.presentation.utils.CalenderUtils
import com.calender.presentation.listener.RecyclerViewItemClickListener
import java.time.LocalDate
import java.time.temporal.ChronoUnit
import kotlin.math.max


class CalenderAdapter : ListAdapter<Calender,CalenderAdapter.MonthView>(diffUtil), RecyclerViewItemClickListener {
    val minDate = LocalDate.of(2000,1,1)
    val maxDate = LocalDate.of(2050,1,1)
    val center = ChronoUnit.MONTHS.between(minDate, LocalDate.now().withDayOfMonth(1)).toInt()
    private var listener: RecyclerViewItemClickListener?= null
    var parentHeight : LiveData<Int>? = null
    inner class MonthView(private val binding: ListCalenderBinding): RecyclerView.ViewHolder(binding.root){
        var dayListAdapter : DayAdapter? = null
        var calenderUtils = CalenderUtils()
        fun bind(position: Int){
            val monthValue = calenderUtils.createMonth(position-center)
            dayListAdapter = DayAdapter(monthValue.month,monthValue.size)
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
        return ChronoUnit.MONTHS.between(minDate, maxDate).toInt()
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

    companion object {
        // diffUtil: currentList에 있는 각 아이템들을 비교하여 최신 상태를 유지하도록 한다.
        val diffUtil = object : DiffUtil.ItemCallback<Calender>() {
            override fun areItemsTheSame(oldItem: Calender, newItem: Calender): Boolean {
                return oldItem.list == newItem.list
            }

            override fun areContentsTheSame(oldItem: Calender, newItem: Calender): Boolean {
                return oldItem == newItem
            }
        }
    }
}
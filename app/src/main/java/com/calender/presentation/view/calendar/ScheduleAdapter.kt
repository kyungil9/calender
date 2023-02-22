package com.calender.presentation.view.calendar

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.calender.domain.model.Schedule
import com.calender.presentation.databinding.ListScheduleItemBinding

class ScheduleAdapter : RecyclerView.Adapter<ScheduleAdapter.ScheduleView>(){
    private val items = ArrayList<Schedule>()

    inner class ScheduleView(private val binding: ListScheduleItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item : Schedule){
            binding.scheduleTime.text = item.startTime.toString()
            binding.scheduleDetail.text = item.detail
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleView {
        return ScheduleView(ListScheduleItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ScheduleView, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun setItems(items:ArrayList<Schedule>){
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }


}
package com.calender.main.ui.adapter

import android.content.Context
import android.graphics.Color
import android.util.Log
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.calender.main.R
import com.calender.main.custom_toast
import com.calender.main.data.entity.Calender
import com.calender.main.data.entity.Daily
import com.calender.main.databinding.ListItemDayBinding
import dagger.hilt.android.qualifiers.ActivityContext
import java.time.LocalDate
import java.util.*
import javax.inject.Inject

class DayAdapter (val tempMonth:Int,val size:Int): ListAdapter<Daily,DayAdapter.DayView>(diffUtil) {
    inner class DayView(private val binding: ListItemDayBinding): RecyclerView.ViewHolder(binding.root){
        init {
            if (size == 42) {
                val params = binding.itemDayLayout.layoutParams
                params.height = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,50F,binding.itemDayLayout.context.resources.displayMetrics).toInt()
                binding.itemDayLayout.layoutParams = params
            }else if(size == 28){
                val params = binding.itemDayLayout.layoutParams
                params.height = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,75F,binding.itemDayLayout.context.resources.displayMetrics).toInt()
                binding.itemDayLayout.layoutParams = params
            }
        }
        fun bind(item : Daily,position: Int){
            binding.itemDayLayout.setOnClickListener {
                Toast.makeText(binding.itemDayLayout.context, "${item.date}", Toast.LENGTH_SHORT).show()
            }
            binding.itemDayText.text = item.date.dayOfMonth.toString()

            binding.itemDayText.setTextColor(when(position % 7) {
                0 -> Color.RED
                6 -> Color.BLUE
                else -> Color.BLACK
            })
            if(tempMonth != item.date.monthValue) {
                binding.itemDayText.alpha = 0.4f
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DayView {
        return DayView(ListItemDayBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: DayView, position: Int) {
        holder.bind(currentList[position],position)
    }

    override fun getItemCount(): Int {
        return currentList.size
    }

    companion object {
        // diffUtil: currentList에 있는 각 아이템들을 비교하여 최신 상태를 유지하도록 한다.
        val diffUtil = object : DiffUtil.ItemCallback<Daily>() {
            override fun areItemsTheSame(oldItem: Daily, newItem: Daily): Boolean {
                return oldItem.date == newItem.date
            }

            override fun areContentsTheSame(oldItem: Daily, newItem: Daily): Boolean {
                return oldItem == newItem
            }
        }
    }
}
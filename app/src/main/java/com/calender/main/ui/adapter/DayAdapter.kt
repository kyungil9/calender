package com.calender.main.ui.adapter

import android.content.Context
import android.graphics.Color
import android.util.Log
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.calender.main.R
import com.calender.main.databinding.ListItemDayBinding
import dagger.hilt.android.qualifiers.ActivityContext
import java.time.LocalDate
import java.util.*
import javax.inject.Inject

class DayAdapter (val tempMonth:Int, val dayList: List<LocalDate>): RecyclerView.Adapter<DayAdapter.DayView>() {
    val row = 6
    inner class DayView(private val binding: ListItemDayBinding): RecyclerView.ViewHolder(binding.root){
        init {
            if (dayList.size == 42) {
                val params = binding.itemDayLayout.layoutParams
                params.height = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,50F,binding.itemDayLayout.context.resources.displayMetrics).toInt()
                binding.itemDayLayout.layoutParams = params
            }else if(dayList.size == 28){
                val params = binding.itemDayLayout.layoutParams
                params.height = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,75F,binding.itemDayLayout.context.resources.displayMetrics).toInt()
                binding.itemDayLayout.layoutParams = params
            }
        }
        fun bind(position: Int){
            binding.itemDayLayout.setOnClickListener {
                Toast.makeText(binding.itemDayLayout.context, "${dayList[position]}", Toast.LENGTH_SHORT).show()
            }
            binding.itemDayText.text = dayList[position].dayOfMonth.toString()

            binding.itemDayText.setTextColor(when(position % 7) {
                0 -> Color.RED
                6 -> Color.BLUE
                else -> Color.BLACK
            })
            if(tempMonth != dayList[position].monthValue) {
                binding.itemDayText.alpha = 0.4f
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DayView {
        return DayView(ListItemDayBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: DayView, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return dayList.size
    }
}
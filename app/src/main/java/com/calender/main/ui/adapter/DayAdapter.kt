package com.calender.main.ui.adapter

import android.graphics.Color
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.calender.main.data.entity.Daily
import com.calender.main.databinding.FragmentCalenderBinding
import com.calender.main.databinding.FragmentHomeBinding
import com.calender.main.databinding.ListCalenderItemBinding
import java.time.LocalDate


class DayAdapter(
    val tempMonth:Int, val size:Int
): ListAdapter<Daily,DayAdapter.DayView>(diffUtil) {
    inner class DayView(private val binding: ListCalenderItemBinding): RecyclerView.ViewHolder(binding.root){
        init {
            if (size == 42) {
                val params = binding.itemDayLayout.layoutParams
                params.height = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,50F,binding.itemDayLayout.context.resources.displayMetrics).toInt()
                binding.itemDayLayout.layoutParams = params
            }else if(size == 28){
                val params = binding.itemDayLayout.layoutParams
                params.height = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,75F,binding.itemDayLayout.context.resources.displayMetrics).toInt()
                binding.itemDayLayout.layoutParams = params
            }else if (size == 7){
                val params = binding.itemDayLayout.layoutParams
                params.height = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,70F,binding.itemDayLayout.context.resources.displayMetrics).toInt()
                binding.itemDayLayout.layoutParams = params
            }
        }
        fun bind(item : Daily,position: Int){
            binding.itemDayLayout.setOnClickListener {
                Toast.makeText(binding.itemDayLayout.context, "${item.date}", Toast.LENGTH_SHORT).show()
                //특정 날짜 데이터를 viewmodel에 삽입 size로 구분

            }
            binding.itemDayText.text = item.date.dayOfMonth.toString()

            if (size == 7){
                binding.itemDayText.setTextColor(
                    when (position % 7) {
                        5 -> Color.BLUE
                        6 -> Color.RED
                        else -> Color.BLACK
                    }
                )
                if(item.date == LocalDate.now()){
                    //오늘날짜 색상바꾸기
                    binding.itemDayText.apply {
                        setBackgroundColor(Color.GREEN)
                        setTextColor(Color.WHITE)
                    }
                }

            }else {
                binding.itemDayText.setTextColor(
                    when (position % 7) {
                        0 -> Color.RED
                        6 -> Color.BLUE
                        else -> Color.BLACK
                    }
                )
            }
            if(tempMonth != item.date.monthValue) {
                binding.itemDayText.alpha = 0.4f
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DayView {
        return DayView(ListCalenderItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
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
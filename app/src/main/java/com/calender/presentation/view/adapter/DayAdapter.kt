package com.calender.presentation.view.adapter

import android.graphics.Color
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.calender.domain.model.Daily
import com.calender.presentation.R
import com.calender.presentation.databinding.ListCalenderItemBinding
import com.calender.presentation.listener.RecyclerViewItemClickListener
import java.time.LocalDate


class DayAdapter(
    val tempMonth:Int, val size:Int
): ListAdapter<Daily,DayAdapter.DayView>(diffUtil), RecyclerViewItemClickListener {
    private var listener: RecyclerViewItemClickListener?= null
    var parentHeight : LiveData<Int>? = null
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
        fun bind(item : Daily, position: Int){
            binding.apply {
                data = item
                items = CalenderItem(tempMonth,parentHeight!!,position % 7,size /7)
                itemDayLayout.setOnClickListener {
                    Toast.makeText(binding.itemDayLayout.context, "${item.date}", Toast.LENGTH_SHORT).show()
                    itemDayLayout.setBackgroundResource(R.drawable.view_edge4)
                    //특정 날짜 데이터를 viewmodel에 삽입 size로 구분
                    onItemClickListener(item.date,it)
                }
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
        val diffUtil = object : DiffUtil.ItemCallback<Daily>() {
            override fun areItemsTheSame(oldItem: Daily, newItem: Daily): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Daily, newItem: Daily): Boolean {
                return oldItem == newItem
            }
        }
    }
}

data class CalenderItem(
    val month : Int,
    val height : LiveData<Int>,
    val color : Int,
    val size : Int
)
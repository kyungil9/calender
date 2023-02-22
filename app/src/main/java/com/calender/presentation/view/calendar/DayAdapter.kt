package com.calender.presentation.view.calendar

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.calender.domain.model.Daily
import com.calender.presentation.R
import com.calender.presentation.databinding.ListCalenderItemBinding
import com.calender.presentation.listener.RecyclerViewItemClickListener
import com.calender.presentation.utils.CustomToast
import java.time.LocalDate


class DayAdapter(
    val tempMonth:Int, val size:Int
): ListAdapter<Daily, DayAdapter.DayView>(diffUtil), RecyclerViewItemClickListener {
    private var listener: RecyclerViewItemClickListener?= null
    var parentHeight : LiveData<Int>? = null
    var selectDay : LiveData<LocalDate>? = null
    inner class DayView(private val binding: ListCalenderItemBinding): RecyclerView.ViewHolder(binding.root){

        fun bind(item : Daily, position: Int){
            binding.apply {
                data = item
                items = CalenderItem(tempMonth,parentHeight!!,position % 7,size /7,selectDay!!)
                itemDayLayout.setOnClickListener {
                    CustomToast.shortToast(binding.itemDayLayout.context, "${item.date}")
                    //itemDayLayout.setBackgroundResource(R.drawable.view_edge4)
                    //특정 날짜 데이터를 viewmodel에 삽입 size로 구분
                    onItemClickListener(item.date)
                }
            }
            //날짜에 맞는 데이터를 찾아서 data에 넣는 형식으로
            selectDay?.observeForever{
                if (item.date == it){
                    binding.itemDayLayout.setBackgroundResource(R.drawable.view_edge4)
                }else{
                    binding.itemDayLayout.setBackgroundResource(R.drawable.viewedge)
                }
            }
            parentHeight?.observeForever {
                val params = binding.itemDayLayout.layoutParams
                params.height = parentHeight?.value!! / (size/7)
                if (params.height <= 200){
                    binding.ScheduleDetailOne.visibility = View.GONE
                    binding.ScheduleDetailTwo.visibility = View.GONE
                    binding.ScheduleDetailThree.visibility = View.GONE
                    item.list.forEachIndexed { index, _ ->
                        when (index) {
                            0 -> binding.scheduleOne.visibility = View.VISIBLE
                            1 -> binding.scheduleTwo.visibility = View.VISIBLE
                            2 -> binding.scheduleThree.visibility = View.VISIBLE
                        }
                    }
                }else{
                    binding.scheduleOne.visibility = View.GONE
                    binding.scheduleTwo.visibility = View.GONE
                    binding.scheduleThree.visibility = View.GONE
                    item.list.forEachIndexed { index, _ ->
                        when (index) {
                            0 -> binding.ScheduleDetailOne.visibility = View.VISIBLE
                            1 -> binding.ScheduleDetailTwo.visibility = View.VISIBLE
                            2 -> binding.ScheduleDetailThree.visibility = View.VISIBLE
                        }
                    }
                }
                binding.itemDayLayout.layoutParams = params
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DayView {
        return DayView(ListCalenderItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: DayView, position: Int) {
        holder.bind(currentList[position],position)
    }
    override fun onItemClickListener(date: LocalDate) {
        listener?.onItemClickListener(date)
    }
    fun setOnItemClickListener(listener : RecyclerViewItemClickListener){
        this.listener=listener
    }
    companion object {
        // diffUtil: currentList에 있는 각 아이템들을 비교하여 최신 상태를 유지하도록 한다.
        val diffUtil = object : DiffUtil.ItemCallback<Daily>() {
            override fun areItemsTheSame(oldItem: Daily, newItem: Daily): Boolean {
                return oldItem.list == newItem.list
            }

            override fun areContentsTheSame(oldItem: Daily, newItem: Daily): Boolean {
                return oldItem.date == newItem.date
            }
        }
    }
}

data class CalenderItem(
    val month : Int,
    val height : LiveData<Int>,
    val color : Int,
    val size : Int,
    val selectDay : LiveData<LocalDate>
)
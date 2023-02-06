package com.calender.presentation.view.adapter

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.calender.domain.model.ToDoCheck
import com.calender.domain.model.ToDoCheckMode
import com.calender.presentation.R
import com.calender.presentation.databinding.ListTodoItemBinding
import com.calender.presentation.listener.RecyclerViewToDoClickListener
import java.time.LocalDate


class ToDoCheckAdapter:ListAdapter<ToDoCheck,ToDoCheckAdapter.CheckView>(diffUtil),RecyclerViewToDoClickListener {
    private var listener: RecyclerViewToDoClickListener? = null

    inner class CheckView(private val binding: ListTodoItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(item: ToDoCheck){
            binding.apply {
                todo = item
                if (item.state == 0 && item.date >= LocalDate.now()){
                    item.state = 1
                    onItemClickListener(item,ToDoCheckMode.STATE)
                }
                todoCheckbox.apply {
                    setOnCheckedChangeListener { _, isChecked ->
                        if (isChecked) {
                            paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
                            item.state = 2
                            todoCheckSeekState.viewSeekbar.visibility = View.GONE
                        } else {
                            paintFlags = (paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv())
                            item.state = 1
                            todoCheckSeekState.viewSeekbar.visibility = View.VISIBLE
                        }
                        onItemClickListener(item, ToDoCheckMode.STATE)
                    }
                }
                todoCheckSeekState.apply {
                    viewSeekbar.visibility = if(item.state != 2) View.VISIBLE else View.GONE
                    seekbarTodo.progress = item.statePercent / 5
                    seekbarTodo.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
                        override fun onStartTrackingTouch(p0: SeekBar?) {
                        }
                        override fun onStopTrackingTouch(p0: SeekBar?) {
                        }
                        override fun onProgressChanged(p0: SeekBar?, progress: Int, p2: Boolean) {
                            item.statePercent = progress * 5
                            seekbarText.text = "진행상태 : " + item.statePercent.toString()
                            onItemClickListener(item, ToDoCheckMode.STATEPERCENT)
                        }
                    })
                }

            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoCheckAdapter.CheckView {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ListTodoItemBinding>(layoutInflater, R.layout.list_todo_item,parent,false)
        return CheckView(binding)
    }

    override fun onBindViewHolder(holder: ToDoCheckAdapter.CheckView, position: Int) {
        holder.bind(currentList[position])
    }

    override fun onItemClickListener(item: ToDoCheck,mode : ToDoCheckMode) {
        listener?.onItemClickListener(item,mode)
    }
    fun setOnItemClickListener(listener: RecyclerViewToDoClickListener){
        this.listener = listener
    }

    companion object {
        // diffUtil: currentList에 있는 각 아이템들을 비교하여 최신 상태를 유지하도록 한다.
        val diffUtil = object : DiffUtil.ItemCallback<ToDoCheck>() {
            override fun areItemsTheSame(oldItem: ToDoCheck, newItem: ToDoCheck): Boolean {
                return oldItem.date == newItem.date
            }

            override fun areContentsTheSame(oldItem: ToDoCheck, newItem: ToDoCheck): Boolean {
                return oldItem.date == newItem.date
            }
        }
    }
}
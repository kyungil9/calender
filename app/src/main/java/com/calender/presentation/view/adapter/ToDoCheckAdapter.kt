package com.calender.presentation.view.adapter

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.calender.domain.model.ToDoCheck
import com.calender.presentation.R
import com.calender.presentation.databinding.ListTodoItemBinding


class ToDoCheckAdapter:ListAdapter<ToDoCheck,ToDoCheckAdapter.CheckView>(diffUtil) {
    inner class CheckView(private val binding: ListTodoItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(item: ToDoCheck){
            binding.apply {
                todo = item
                todoCheckbox.apply {
                    setOnCheckedChangeListener { compoundButton, isChecked ->
                        if (isChecked) {
                            paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
                        } else {
                            paintFlags = (paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv())
                        }
                    }
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
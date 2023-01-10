package com.calender.main.ui.adapter

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.calender.data.model.ToDoCheck
import com.calender.main.databinding.ListTodoItemBinding

class ToDoCheckAdapter:ListAdapter<ToDoCheck,ToDoCheckAdapter.CheckView>(diffUtil) {
    inner class CheckView(private val binding: ListTodoItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(item: ToDoCheck, position: Int){
            binding.todoCheckbox.apply {
                text = item.doIt
                isChecked = item.check
                if(isChecked){
                    paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
                }
                setOnCheckedChangeListener { compoundButton, isChecked ->
                    if(isChecked){
                        paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
                    }else{
                        paintFlags = (paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv())
                    }
                }
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoCheckAdapter.CheckView {
        return CheckView(ListTodoItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ToDoCheckAdapter.CheckView, position: Int) {
        holder.bind(currentList[position],position)
    }

    override fun getItemCount(): Int {
        return currentList.size
    }

    companion object {
        // diffUtil: currentList에 있는 각 아이템들을 비교하여 최신 상태를 유지하도록 한다.
        val diffUtil = object : DiffUtil.ItemCallback<ToDoCheck>() {
            override fun areItemsTheSame(oldItem: ToDoCheck, newItem: ToDoCheck): Boolean {
                return oldItem.date == newItem.date
            }

            override fun areContentsTheSame(oldItem: ToDoCheck, newItem: ToDoCheck): Boolean {
                return oldItem == newItem
            }
        }
    }
}
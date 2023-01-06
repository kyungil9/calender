package com.calender.main.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.calender.main.data.entity.Daily
import com.calender.main.data.entity.ToDo
import com.calender.main.databinding.ListTodoBinding

class ToDoAdapter : ListAdapter<ToDo, ToDoAdapter.DoView>(diffUtil){

    inner class DoView(private val binding: ListTodoBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(item:ToDo,position: Int){

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DoView {
        return DoView(ListTodoBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: DoView, position: Int) {
        holder.bind(currentList[position],position)
    }

    override fun getItemCount(): Int {
        return currentList.size
    }

    companion object {
        // diffUtil: currentList에 있는 각 아이템들을 비교하여 최신 상태를 유지하도록 한다.
        val diffUtil = object : DiffUtil.ItemCallback<ToDo>() {
            override fun areItemsTheSame(oldItem: ToDo, newItem: ToDo): Boolean {
                return oldItem.date == newItem.date
            }

            override fun areContentsTheSame(oldItem: ToDo, newItem: ToDo): Boolean {
                return oldItem == newItem
            }
        }
    }
}
package com.calender.presentation.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.calender.domain.model.ToDo
import com.calender.presentation.databinding.ListTodoBinding
import com.calender.presentation.utils.HorizonItemDecorator

class ToDoAdapter : ListAdapter<ToDo, ToDoAdapter.DoView>(diffUtil){

    inner class DoView(private val binding: ListTodoBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(item: ToDo, position: Int){
            val todoChecklistAdapter = ToDoCheckAdapter()
            val checkManager = LinearLayoutManager(binding.itemTodoList.context, LinearLayoutManager.VERTICAL,false)
            binding.itemTodoList.apply {
                adapter = todoChecklistAdapter
                layoutManager = checkManager
                addItemDecoration(HorizonItemDecorator(15))
            }
            binding.todoListName.text = "${item.date.monthValue}.${item.date.dayOfMonth}(${item.date.dayOfWeek})"
            todoChecklistAdapter.submitList(item.list)
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
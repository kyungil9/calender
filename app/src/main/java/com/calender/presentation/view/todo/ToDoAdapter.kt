package com.calender.presentation.view.todo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.*
import com.calender.domain.model.ToDo
import com.calender.domain.model.ToDoCheck
import com.calender.domain.model.ToDoCheckMode
import com.calender.presentation.R
import com.calender.presentation.databinding.ListTodoBinding
import com.calender.presentation.listener.RecyclerViewToDoClickListener
import com.calender.presentation.utils.HorizonItemDecorator
import com.calender.presentation.utils.VerticalItemDecorator

class ToDoAdapter : ListAdapter<ToDo, ToDoAdapter.DoView>(diffUtil),RecyclerViewToDoClickListener{
    private var listener : RecyclerViewToDoClickListener? = null
    inner class DoView(private val binding: ListTodoBinding): RecyclerView.ViewHolder(binding.root){
        private val todoChecklistAdapter = ToDoCheckAdapter()
        fun bind(item: ToDo){
            binding.apply {
                vm = item
                dummy = emptyList<ToDoCheck>()
                adapter = todoChecklistAdapter
                mode = false
                itemTodoList.apply {
                    addItemDecoration(HorizonItemDecorator(10))
                    addItemDecoration(VerticalItemDecorator(10))
                }
            }
            todoChecklistAdapter.setOnItemClickListener(listener!!)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DoView {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ListTodoBinding>(layoutInflater, R.layout.list_todo,parent,false)
        return DoView(binding)
    }

    override fun onBindViewHolder(holder: DoView, position: Int) {
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
        val diffUtil = object : DiffUtil.ItemCallback<ToDo>() {
            override fun areItemsTheSame(oldItem: ToDo, newItem: ToDo): Boolean {
                return oldItem.title == newItem.title
            }

            override fun areContentsTheSame(oldItem: ToDo, newItem: ToDo): Boolean {
                return oldItem.list == newItem.list
            }
        }
    }
}
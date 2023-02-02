package com.calender.presentation.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.calender.domain.model.ToDoCheck
import com.calender.presentation.R
import com.calender.presentation.databinding.ListTagItemBinding

class TagAdapter : ListAdapter<String,TagAdapter.CheckView>(diffUtil) {

    inner class CheckView(private val binding : ListTagItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item : String){

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CheckView {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ListTagItemBinding>(layoutInflater, R.layout.list_tag_item,parent,false)
        return CheckView(binding)
    }

    override fun onBindViewHolder(holder: CheckView, position: Int) {
        holder.bind(currentList[position])
    }


    companion object {
        // diffUtil: currentList에 있는 각 아이템들을 비교하여 최신 상태를 유지하도록 한다.
        val diffUtil = object : DiffUtil.ItemCallback<String>() {
            override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: String , newItem: String): Boolean {
                return oldItem == newItem
            }
        }
    }
}
package com.calender.presentation.view.adapter

import android.util.Log
import android.util.SparseBooleanArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.calender.domain.model.ToDoCheck
import com.calender.presentation.R
import com.calender.presentation.databinding.ListTagItemBinding
import com.calender.presentation.listener.RecyclerViewItemClickListener
import com.calender.presentation.listener.RecyclerViewTagClickListener
import java.time.LocalDate

class TagAdapter : ListAdapter<String,TagAdapter.CheckView>(diffUtil), RecyclerViewTagClickListener {
    private var listener: RecyclerViewTagClickListener?= null
    private var lastPosition = -1
    private var currentPosition = -1
    inner class CheckView(private val binding : ListTagItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item : String){
            binding.apply {
                tag = item
                linearTag.setOnClickListener {
                    if (checkTagView.visibility == View.INVISIBLE) {
                        lastPosition = currentPosition
                        onItemClickListener(textviewTag.text.toString(),adapterPosition)
                        currentPosition = adapterPosition
                        binding.checkTagView.visibility = View.VISIBLE
                        notifyItemChanged(lastPosition)
                    }
                }
            }
            if(currentPosition == adapterPosition){
                binding.checkTagView.visibility = View.VISIBLE
            }else if(lastPosition == adapterPosition){
                binding.checkTagView.visibility = View.INVISIBLE
            }
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

    override fun onItemClickListener(tag : String,index: Int) {
        listener?.onItemClickListener(tag,index)
    }

    fun setOnItemClickListener(listener : RecyclerViewTagClickListener){
        this.listener=listener
    }

    fun setDefaultTag(index : Int){
        lastPosition = index
        currentPosition = index
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
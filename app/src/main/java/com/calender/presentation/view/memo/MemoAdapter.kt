package com.calender.presentation.view.memo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.calender.domain.model.Calender
import com.calender.domain.model.Memo
import com.calender.presentation.databinding.ListMemoBinding

class MemoAdapter :ListAdapter<Memo,MemoAdapter.MemoView>(diffUtil){

    inner class MemoView(private val binding : ListMemoBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item : Memo){

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemoView {
        return MemoView(ListMemoBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: MemoView, position: Int) {
        holder.bind(currentList[position])
    }


    companion object {
        // diffUtil: currentList에 있는 각 아이템들을 비교하여 최신 상태를 유지하도록 한다.
        val diffUtil = object : DiffUtil.ItemCallback<Memo>() {
            override fun areItemsTheSame(oldItem: Memo, newItem: Memo): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Memo, newItem: Memo): Boolean {
                return oldItem == newItem
            }
        }
    }
}
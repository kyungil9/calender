package com.calender.presentation.utils

import android.graphics.Paint
import android.view.View
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.NumberPicker
import android.widget.ProgressBar
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.calender.domain.model.*
import com.calender.presentation.R
import com.calender.presentation.view.adapter.ToDoAdapter
import com.calender.presentation.view.adapter.ToDoCheckAdapter
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import java.time.LocalDate

@BindingAdapter("tags")
fun ChipGroup.bindTags(tags : List<String>?){
    tags?.forEach{tag->
        val tagView : Chip = Chip(context).apply {
            text = tag
            isCheckable = true
            setOnCheckedChangeListener{ value,checked ->
                if(checked){
                    //추후 서버에 기록 저장부분 처리추가
                }else{

                }
            }
        }
        addView(tagView)
    }
}

@BindingAdapter("show")
fun ProgressBar.bindShow(uiState : Result<*>){
    visibility = if (uiState is Result.Loading) View.VISIBLE else View.GONE
}

@BindingAdapter("adapter")
fun RecyclerView.bindAdapter(adapter : RecyclerView.Adapter<*>){
    this.adapter = adapter
}

@BindingAdapter("toDoItems")
fun RecyclerView.bindToDoItems(list : List<ToDo>?){
    val adapter = this.adapter
    if(adapter is ToDoAdapter){
        adapter.submitList(list)
    }
}

@BindingAdapter("toDoCheckItems")
fun RecyclerView.bindToDoCheckItems(list : List<ToDoCheck>?){
    val adapter = this.adapter
    if(adapter is ToDoCheckAdapter){
        adapter.submitList(list)
    }
}

@BindingAdapter("toDoChecked")
fun CheckBox.bindToDoChecked(value : Int){
    this.isChecked = value == 3
    if(this.isChecked)
        this.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
}

@BindingAdapter("toDoMode")
fun TextView.bindToDoMode(todo : ToDo){
    if (todo.title.isEmpty())
        this.text = "${todo.date.monthValue}.${todo.date.dayOfMonth}(${todo.date.dayOfWeek})"
    else
        this.text = todo.title
}

@BindingAdapter("viewMode")
fun ImageView.bindViewMode(mode : ViewMode){
    when(mode){
        ViewMode.ALARM -> this.setImageResource(R.drawable.ic_baseline_alarm_24)
        ViewMode.TAG -> this.setImageResource(R.drawable.ic_baseline_folder_24)
    }
}

@BindingAdapter(value =["npMode","np"], requireAll = true)
fun NumberPicker.bindNpMode(mode : NpMode,np : NumberPick){
    this.minValue =0
    when(mode){
        NpMode.YEAR ->{
            this.maxValue = np.getYearSize()
            this.wrapSelectorWheel = false
            this.displayedValues = np.getYearValue()
            this.value = np.getYearCurrent()
        }
        NpMode.MONTH ->{
            this.maxValue = np.getMonthSize()
            this.wrapSelectorWheel = true
            this.displayedValues = np.getMonthValue()
            this.value = np.getMonthCurrent()
        }
        NpMode.DAY -> {
            this.maxValue = np.getDaySize()
            this.wrapSelectorWheel = true
            this.displayedValues = np.getDayValue()
            this.value = np.getDayCurrent()
        }
    }
}

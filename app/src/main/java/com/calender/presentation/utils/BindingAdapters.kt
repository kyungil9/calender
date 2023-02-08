package com.calender.presentation.utils

import android.graphics.Paint
import android.util.Log
import android.view.View
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.NumberPicker
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.view.get
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.calender.domain.model.*
import com.calender.presentation.R
import com.calender.presentation.view.adapter.TagAdapter
import com.calender.presentation.view.adapter.ToDoAdapter
import com.calender.presentation.view.adapter.ToDoCheckAdapter
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import org.jetbrains.anko.custom.style
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@BindingAdapter("tags")
fun ChipGroup.bindTags(tags : List<String>?){
    tags?.forEach{tag->
        val tagView : Chip = Chip(context).apply {
            text = tag
            isCheckable = true
        }
        addView(tagView)
    }
}

@BindingAdapter("recordTags")
fun ChipGroup.bindRecordTags(tags : Result<List<String>>){
    removeAllViews()
    if (tags is Result.Success<*>){
        tags.successOrNull()?.forEach{ tag->
            val tagView : Chip = Chip(context).apply {
                text = tag
                isCheckable = true
            }
            addView(tagView)
        }
    }
}

@BindingAdapter("recordCheck")
fun ChipGroup.bindRecordCheck(result : Result<*>){
    if (result is Result.Success<*>){
        val record = result.data as Record
        check(get(0).id)
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
fun RecyclerView.bindToDoItems(result : Result<*>){
    val adapter = this.adapter
    if(adapter is ToDoAdapter && result is Result.Success<*>){
        adapter.submitList(result.data as List<ToDo>)
    }
}

@BindingAdapter("toDoCheckItems")
fun RecyclerView.bindToDoCheckItems(list : List<ToDoCheck>?){
    val adapter = this.adapter
    if(adapter is ToDoCheckAdapter){
        adapter.submitList(list)
    }
}

@BindingAdapter("tagItems")
fun RecyclerView.bindTagItems(result : Result<*>){
    val adapter = this.adapter
    if (adapter is TagAdapter && result is Result.Success<*>){
        adapter.submitList(result.data as List<String>)
    }
}

@BindingAdapter("toDoChecked")
fun CheckBox.bindToDoChecked(value : Int){
    this.isChecked = value == 2
    if(this.isChecked)
        this.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
}

@BindingAdapter("toDoMode")
fun TextView.bindToDoMode(todo : ToDo){
    if (todo.title.isEmpty())
        this.text = "${todo.date.monthValue}.${todo.date.dayOfMonth}(${Calender.transDayToKorean(todo.date.dayOfWeek.value)})"
    else
        this.text = todo.title
}

@BindingAdapter("viewMode")
fun ImageView.bindViewMode(mode : ViewMode){
    when(mode){
        ViewMode.ALARM -> this.setImageResource(R.drawable.ic_baseline_notifications_24)
        ViewMode.TAG -> this.setImageResource(R.drawable.ic_baseline_folder_24)
        ViewMode.DATE,ViewMode.END -> this.setImageResource(R.drawable.ic_baseline_alarm_24)
        ViewMode.STATE -> this.setImageResource(R.drawable.ic_baseline_check_circle_24)
        ViewMode.REPEAT -> this.setImageResource(R.drawable.ic_baseline_repeat_24)
        else -> this.visibility = View.INVISIBLE
    }
}

@BindingAdapter("viewModeVisibility")
fun ImageView.bindViewModeVisibility(mode : ViewMode){
    when(mode){
        ViewMode.DATE -> this.setImageResource(R.drawable.ic_baseline_arrow_drop_down_24)
        else -> this.visibility = View.VISIBLE
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

@BindingAdapter("viewRecord")
fun ImageView.bindViewRecord(result: Result<*>){
    if(result is Result.Success<*>){
        val tag = result.data as Record
        when(tag.tag){
            "공부" -> this.setImageResource(R.drawable.ic_baseline_study)
            "운동" -> this.setImageResource(R.drawable.ic_baseline_exercise)
            "휴식" -> this.setImageResource(R.drawable.ic_baseline_person_outline_24)
            "이동시간" -> this.setImageResource(R.drawable.ic_baseline_movecar)
            "개인일정" -> this.setImageResource(R.drawable.ic_baseline_promise)
            "수면" -> this.setImageResource(R.drawable.ic_baseline_rest)
            "식사" -> this.setImageResource(R.drawable.ic_baseline_dining_24)
            else -> this.setImageResource(R.drawable.ic_baseline_repeat_24)
        }
    }
}

@BindingAdapter("textRecord")
fun TextView.bindTextRecord(result: Result<*>){
    if (result is Result.Success<*>){
        val startDate = result.data as Record
        this.text = startDate.startTime.format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss"))
    }
}
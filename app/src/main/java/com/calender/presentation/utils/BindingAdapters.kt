package com.calender.presentation.utils

import android.graphics.Color
import android.graphics.Paint
import android.view.View
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.NumberPicker
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.core.view.get
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.calender.domain.model.*
import com.calender.presentation.R
import com.calender.presentation.view.calendar.CalenderAdapter
import com.calender.presentation.view.addtodo.TagAdapter
import com.calender.presentation.view.memo.MemoAdapter
import com.calender.presentation.view.todo.ToDoAdapter
import com.calender.presentation.view.todo.ToDoCheckAdapter
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import java.time.Duration
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@BindingAdapter(value =["tags","defaultCheck"], requireAll = true)
fun ChipGroup.bindTags(tags : List<String>?,defaultCheck : Boolean){
    tags?.forEach{tag->
        val tagView : Chip = Chip(context).apply {
            text = tag
            isCheckable = true
        }
        addView(tagView)
    }
    if (defaultCheck)
        check(get(0).id)
}

@BindingAdapter(value = ["recordTags","selectTags"], requireAll = true)
fun ChipGroup.bindRecordTags(tags : Result<List<String>>,selectTags : Result<Record>){
    removeAllViews()
    if (tags is Result.Success<*>){
        var select = -1
        tags.successOrNull()?.forEachIndexed{ index,tag->
            val tagView : Chip = Chip(context).apply {
                text = tag
                isCheckable = true
            }
            addView(tagView)
            if (selectTags is Result.Success<*>){
                val selectTag = selectTags.successOrNull()
                if (selectTag?.tag == tag)
                    select = index
            }
        }
        if(select != -1){
            check(get(select).id)
        }
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


@BindingAdapter("tagItems")
fun RecyclerView.bindTagItems(result : Result<*>){
    val adapter = this.adapter
    if (adapter is TagAdapter && result is Result.Success<*>){
        adapter.submitList(result.data as List<String>)
    }
}

@BindingAdapter("calenderItems")
fun RecyclerView.bindCalenderItems(data : Calender){
    val adapter = this.adapter
    if (adapter is CalenderAdapter)
        adapter.submitList(listOf(data))
}

@BindingAdapter("memoItems")
fun RecyclerView.bindMemoItems(result: Result<*>){
    val adapter = this.adapter
    if (adapter is MemoAdapter && result is Result.Success<*>){
        adapter.submitList(result.data as List<Memo>)
    }
}

@BindingAdapter(value = ["toDoCheckItems","toDoHomeItems"], requireAll = true)
fun RecyclerView.bindToDoCheckItems(todo: ToDo, list: List<ToDoCheck>){
    val adapter = this.adapter
    if(adapter is ToDoCheckAdapter){
        if (todo.title != "-1234567"){
            adapter.submitList(todo.list)
        }else{
            adapter.submitList(list)
        }
    }
}
@BindingAdapter("toDoChecked")
fun CheckBox.bindToDoChecked(value : Int){
    this.isChecked = value == 2
    if(this.isChecked)
        this.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
}

@BindingAdapter(value = ["toDoMode","oneDate"], requireAll = true)
fun TextView.bindToDoMode(todo : ToDo,today: LocalDate){
    if (today.year == 2000 && today.monthValue == 1) {
        if (todo.title.isEmpty())
            this.text = "${todo.date.monthValue}.${todo.date.dayOfMonth}(${CalenderUtils.transDayToKorean(todo.date.dayOfWeek.value)})"
        else
            this.text = todo.title
    }else{
        this.text = "${today.monthValue}.${today.dayOfMonth}(${CalenderUtils.transDayToKorean(today.dayOfWeek.value)})"
    }
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
            else -> this.setImageResource(R.drawable.ic_baseline_help_center_24)
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

@BindingAdapter("textRecordTime")
fun TextView.bindTextRecordTime(duration : Duration){
    if (duration != Duration.ZERO){
        this.text = "진행 시간 : " + duration.toHours() + "시 " + duration.toMinutes() % 60 + "분 ( " + duration.seconds % 60 +" )"
    }
}

@BindingAdapter(value = ["viewHomeToDo","viewHomeMode"], requireAll = true)
fun LinearLayoutCompat.bindViewHome(list : List<ToDoCheck>, mode : Boolean){
    if (mode && list.isEmpty()){
        this.visibility = View.GONE
    }else if(mode){
        this.visibility = View.VISIBLE
    }
}


@BindingAdapter("textCalenderColor")
fun TextView.bindTextCalenderColor(color : Int){
    this.setTextColor(
        when(color){
            0,7 -> Color.RED
            6 -> Color.BLUE
            else -> Color.BLACK
        }
    )
}
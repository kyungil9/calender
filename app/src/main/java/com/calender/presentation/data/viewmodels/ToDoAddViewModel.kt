package com.calender.presentation.data.viewmodels

import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.calender.domain.model.ToDoCheck
import com.calender.domain.usecase.InsertToDoUseCase
import com.calender.presentation.utils.NumberPick
import com.google.android.material.chip.Chip
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class ToDoAddViewModel @Inject constructor(
    private val insertToDoUseCase: InsertToDoUseCase
) : ViewModel() {
    private val mutableStartDate = MutableLiveData<LocalDate>()
    private val mutableStartCalenderToggle = MutableLiveData<Boolean>()
    private val mutableEndDate = MutableLiveData<LocalDate>()
    private val mutableEndCalenderToggle = MutableLiveData<Boolean>()
    val liveStartCalenderToggle : LiveData<Boolean> get() = mutableStartCalenderToggle
    val liveStartDate : LiveData<LocalDate> get() = mutableStartDate
    val liveEndCalenderToggle : LiveData<Boolean> get() = mutableEndCalenderToggle
    val liveEndDate : LiveData<LocalDate> get() = mutableEndDate

    val inputDo = MutableLiveData<String>()
    val startNp = NumberPick()
    val endNp = NumberPick()

    val calenderTags = listOf<String>("오늘","내일","다음주")
    val stateTags = listOf<String>("시작전","진행중","완료")
    val endDateTags = listOf<String>("오늘","내일","다음주","기타","무기한")
    val repeatTags = listOf<String>("매일","매주","매년")

    private val toDoCheck = ToDoCheck()


    init {
        mutableStartDate.value = startNp.getToday()
        mutableStartCalenderToggle.value = false
        mutableEndDate.value = endNp.getToday()
        mutableEndCalenderToggle.value = false
    }

    fun loadStartData(){
        mutableStartDate.value = startNp.getToday()
    }
    fun loadEndData(){
        mutableEndDate.value = endNp.getToday()
    }
    fun calenderStartToggleVisibility(){
        mutableStartCalenderToggle.value = mutableStartCalenderToggle.value != true
    }
    fun calenderEndToggleVisibility(){
        mutableEndCalenderToggle.value = mutableEndCalenderToggle.value != true
    }

    fun setRepeat(value : String){
        if(value.isEmpty())
            toDoCheck.repeat = 0
        else
            toDoCheck.repeat = repeatTags.indexOf(value) +1
    }

    fun setState(value: String){
        if(value.isEmpty())
            toDoCheck.state = 0
        else
            toDoCheck.state = stateTags.indexOf(value)
    }

    fun registerToDo(){
        toDoCheck.apply {
            date = liveStartDate.value!!
            doIt = inputDo.value!!
            tag = ""
            endDate = liveEndDate.value!!
        }
        viewModelScope.launch(Dispatchers.IO){
            insertToDoUseCase(toDoCheck)
        }
    }

}
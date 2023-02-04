package com.calender.presentation.data.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.calender.domain.model.ToDoCheck
import com.calender.domain.usecase.tag.GetOneTagUseCase
import com.calender.domain.usecase.todo.InsertToDoUseCase
import com.calender.presentation.utils.NumberPick
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class ToDoAddViewModel @Inject constructor(
    private val insertToDoUseCase: InsertToDoUseCase,
    private val getOneTagUseCase: GetOneTagUseCase
) : ViewModel() {
    private val mutableStartDate = MutableLiveData<LocalDate>()
    private val mutableStartCalenderToggle = MutableLiveData<Boolean>()
    private val mutableEndDate = MutableLiveData<LocalDate>()
    private val mutableEndCalenderToggle = MutableLiveData<Boolean>()
    private val mutableTag = MutableLiveData<String>()
    private val mutableState = MutableLiveData<Int>()
    val liveStartCalenderToggle : LiveData<Boolean> get() = mutableStartCalenderToggle
    val liveStartDate : LiveData<LocalDate> get() = mutableStartDate
    val liveEndCalenderToggle : LiveData<Boolean> get() = mutableEndCalenderToggle
    val liveEndDate : LiveData<LocalDate> get() = mutableEndDate
    val liveTag : LiveData<String> get() = mutableTag
    val liveState : LiveData<Int> get() = mutableState

    val inputDo = MutableLiveData<String>()
    val startNp = NumberPick()
    val endNp = NumberPick()

    val calenderTags = listOf<String>("오늘","내일","다음주")
    val stateTags = listOf<String>("시작전","진행중","완료")
    val endDateTags = listOf<String>("오늘","내일","다음주","다음달")
    val repeatTags = listOf<String>("매일","매주","매년")

    private val toDoCheck = ToDoCheck()
    var tagIndex = 0
    init {
        mutableStartDate.value = startNp.getToday()
        mutableStartCalenderToggle.value = false
        mutableEndDate.value = endNp.getToday()
        mutableEndCalenderToggle.value = false
        mutableState.value = 0
        viewModelScope.launch(Dispatchers.IO) {
            mutableTag.postValue(getOneTagUseCase())
        }
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
    fun setTag(tag : String){
        mutableTag.value = tag
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

    fun setStateProcessing(value : Int){
        mutableState.value = value
    }

    fun registerToDo(){
        toDoCheck.apply {
            date = liveStartDate.value!!
            doIt = inputDo.value!!
            tag = liveTag.value!!
            endDate = liveEndDate.value!!
            statePercent = if (state == 1) liveState.value!! else 0
        }
        viewModelScope.launch(Dispatchers.IO){
            insertToDoUseCase(toDoCheck)
        }
    }

}
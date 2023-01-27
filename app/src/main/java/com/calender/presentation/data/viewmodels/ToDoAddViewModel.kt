package com.calender.presentation.data.viewmodels

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.calender.presentation.utils.NumberPick
import dagger.hilt.android.lifecycle.HiltViewModel
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class ToDoAddViewModel @Inject constructor(

) : ViewModel() {
    private val mutableStartDate = MutableLiveData<LocalDate>()
    private val mutableStartCalenderToggle = MutableLiveData<Boolean>()
    private val mutableEndDate = MutableLiveData<LocalDate>()
    private val mutableEndCalenderToggle = MutableLiveData<Boolean>()
    val liveStartCalenderToggle : LiveData<Boolean> get() = mutableStartCalenderToggle
    val liveStartDate : LiveData<LocalDate> get() = mutableStartDate
    val liveEndCalenderToggle : LiveData<Boolean> get() = mutableEndCalenderToggle
    val liveEndDate : LiveData<LocalDate> get() = mutableEndDate


    val startNp = NumberPick()
    val endNp = NumberPick()


    val calenderTags = listOf<String>("오늘","내일","다음주")
    val stateTags = listOf<String>("시작전","진행중","완료")
    val endDateTags = listOf<String>("오늘","내일","다음주","기타","무기한")
    val repeatTags = listOf<String>("매일","매주","매년")
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



}
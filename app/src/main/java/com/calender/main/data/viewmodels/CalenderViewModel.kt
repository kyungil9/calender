package com.calender.main.data.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.calender.domain.model.Daily
import com.calender.domain.model.Schedule
import com.calender.domain.usecase.GetSearchScheduleUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class CalenderViewModel @Inject constructor(
    private val getSearchScheduleUseCase: GetSearchScheduleUseCase
    ) :ViewModel(){

    private val mutableCalenderInfo = MutableLiveData<ArrayList<Daily>>()
    private val mutableScheduleInfo = MutableLiveData<ArrayList<Schedule>>()
    val calenderInfo:LiveData<ArrayList<Daily>> get() = mutableCalenderInfo
    val scheduleInfo:LiveData<ArrayList<Schedule>> get() = mutableScheduleInfo

    fun getAllScheduleInfo() {//수정
        mutableCalenderInfo.value?.clear()
        //mutableCalenderInfo.value?.addAll()
    }

    fun searchScheduleDate(date: LocalDate){
        mutableScheduleInfo.value?.clear()
        mutableScheduleInfo.value?.addAll(getSearchScheduleUseCase(date))
    }


}
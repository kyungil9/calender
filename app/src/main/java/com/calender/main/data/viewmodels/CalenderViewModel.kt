package com.calender.main.data.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.calender.data.model.Daily
import com.calender.data.model.Schedule
import com.calender.data.repository.CalenderRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class CalenderViewModel @Inject constructor(private val calenderRepositoryImpl : CalenderRepositoryImpl) :ViewModel(){
    private val mutableCalenderInfo = MutableLiveData<ArrayList<Daily>>()
    private val mutableScheduleInfo = MutableLiveData<ArrayList<Schedule>>()
    val calenderInfo:LiveData<ArrayList<Daily>> get() = mutableCalenderInfo
    val scheduleInfo:LiveData<ArrayList<Schedule>> get() = mutableScheduleInfo

    fun getAllScheduleInfo() {//수정
        mutableCalenderInfo.value?.clear()
        mutableCalenderInfo.value?.addAll(calenderRepositoryImpl.getAllDailyData())
    }

    fun searchScheduleDate(date: LocalDate){
        mutableScheduleInfo.value?.clear()
        mutableScheduleInfo.value?.addAll(calenderRepositoryImpl.getSearchSchedule(date))
    }


}
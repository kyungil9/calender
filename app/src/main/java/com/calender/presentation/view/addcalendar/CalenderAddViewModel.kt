package com.calender.presentation.view.addcalendar

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.calender.domain.model.Schedule
import com.calender.domain.usecase.calender.InsertScheduleUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalTime
import javax.inject.Inject

@HiltViewModel
class CalenderAddViewModel @Inject constructor(
    private val insertScheduleUseCase: InsertScheduleUseCase
): ViewModel(){
    private val mutableTag = MutableLiveData<String>()
    private val mutableStartTime = MutableLiveData<LocalTime>()
    private val mutableEndTime = MutableLiveData<LocalTime>()

    val inputCalender = MutableLiveData<String>()
    val liveTag : LiveData<String> get() = mutableTag
    val liveStartTime : LiveData<LocalTime> get() = mutableStartTime
    val liveEndTime : LiveData<LocalTime> get() = mutableEndTime

    val repeatTags = listOf<String>("매일","매주","매월","매년")
    val schedule = Schedule()



    init {
        mutableTag.value =""
        mutableStartTime.value = LocalTime.of(0,0,0)
        mutableEndTime.value = LocalTime.of(0,0,0)
    }
    fun setTimePeriod(startTime : LocalTime,endTime: LocalTime){
        mutableStartTime.value = startTime
        mutableEndTime.value = endTime
        schedule.timeSelect = true
    }
    fun clearTime(){
        mutableStartTime.value = LocalTime.of(0,0,0)
        mutableEndTime.value = LocalTime.of(0,0,0)
        schedule.timeSelect = false
    }
    fun setRepeat(repeat : String){
        if(repeat.isEmpty())
            schedule.repeat = 0
        else
            schedule.repeat = repeatTags.indexOf(repeat) +1
    }

    fun registerSchedule(){
        schedule.apply {
            detail = inputCalender.value!!
            startTime = liveStartTime.value!!
            endTime = liveEndTime.value!!

        }
        viewModelScope.launch(Dispatchers.IO) {
            insertScheduleUseCase(schedule)
        }
    }

}
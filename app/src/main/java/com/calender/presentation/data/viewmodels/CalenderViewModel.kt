package com.calender.presentation.data.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.calender.domain.model.*
import com.calender.domain.usecase.GetSearchScheduleUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.stateIn
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class CalenderViewModel @Inject constructor(
    private val getSearchScheduleUseCase: GetSearchScheduleUseCase
    ) :ViewModel(){

    val scheduleResult : StateFlow<Result<List<Schedule>>> = getSearchScheduleUseCase(LocalDate.now())
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = Result.Loading
        )

    val scheduleInfo : StateFlow<List<Schedule?>> = scheduleResult.mapLatest { state ->
        state.successOrNull() ?: emptyList<Schedule>()
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000L),
        initialValue = emptyList<Schedule>()
    )





    fun getAllScheduleInfo() {//수정
        //mutableCalenderInfo.value?.clear()
        //mutableCalenderInfo.value?.addAll()
    }

    fun searchScheduleDate(date: LocalDate){
        //mutableScheduleInfo.value?.clear()
        //mutableScheduleInfo.value?.addAll(getSearchScheduleUseCase(date))
    }


}
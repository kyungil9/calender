package com.calender.presentation.data.viewmodels

import android.app.Application
import android.util.TypedValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.calender.domain.model.*
import com.calender.domain.usecase.calender.GetMonthScheduleUseCase
import com.calender.domain.usecase.calender.GetSearchScheduleUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.temporal.ChronoUnit
import javax.inject.Inject

@HiltViewModel
class CalenderViewModel @Inject constructor(
    application: Application,
    private val getSearchScheduleUseCase: GetSearchScheduleUseCase,
    private val getMonthScheduleUseCase: GetMonthScheduleUseCase
    ) :ViewModel(){
    private val mutableHeight = MutableLiveData<Int>()
    private val mutableSelectDay = MutableLiveData<LocalDate>()
    var parentHeight = 0
    val liveHeight : LiveData<Int> get() = mutableHeight
    val liveSelectDay : LiveData<LocalDate> get() = mutableSelectDay
    val scheduleResult : StateFlow<Result<List<Schedule>>> = getSearchScheduleUseCase(LocalDate.now())
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = Result.Loading
        )
    var mode = 0
    var position = Int.MAX_VALUE / 2
    init {
        mutableSelectDay.value = LocalDate.now()
        mutableHeight.value = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,50F,application.resources.displayMetrics).toInt()
    }

    fun setViewHeight(height : Int){
        mutableHeight.value = height
    }
    fun setSelectDay(date: LocalDate){
        mutableSelectDay.value = date
    }

    fun updatePosition(date : LocalDate){
        position += ChronoUnit.MONTHS.between(liveSelectDay.value,date).toInt()
        setSelectDay(date)
    }

    fun getMonthSchedule(startDate: LocalDate , endDate: LocalDate){
        viewModelScope.launch {
            getMonthScheduleUseCase(startDate, endDate).collectLatest {
                when(it){
                    is Result.Success<*> ->{
                        val list = it.successOrNull()

                    }
                }
            }
        }
    }
}
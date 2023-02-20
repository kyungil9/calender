package com.calender.presentation.data.viewmodels

import android.app.Application
import android.util.TypedValue
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.calender.domain.model.*
import com.calender.domain.usecase.calender.GetMonthScheduleUseCase
import com.calender.domain.usecase.calender.GetSearchScheduleUseCase
import com.calender.presentation.utils.CalenderUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.*
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
    private val mutableMonthSchedule = MutableStateFlow(Calender())
    var parentHeight = 0
    val liveHeight : LiveData<Int> get() = mutableHeight
    val liveSelectDay : LiveData<LocalDate> get() = mutableSelectDay
    val liveMonthSchedule = mutableMonthSchedule.asStateFlow()
    val scheduleResult : StateFlow<Result<List<Schedule>>> = getSearchScheduleUseCase(LocalDate.now())
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = Result.Loading
        )
    var mode = 0
    var position = ChronoUnit.MONTHS.between(LocalDate.of(2000,1,1), LocalDate.now().withDayOfMonth(1)).toInt()
    var lastView :View? = null
    var pastDay = LocalDate.now()
    init {
        mutableSelectDay.value = LocalDate.now()
        mutableHeight.value = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,50F,application.resources.displayMetrics).toInt()
        val tempDate = CalenderUtils.getMonthPeriod(LocalDate.now())
        getMonthSchedule(tempDate.startDate,tempDate.endDate)
    }

    fun setViewHeight(height : Int){
        mutableHeight.value = height
    }
    fun setSelectDay(date: LocalDate){
        pastDay = liveSelectDay.value
        mutableSelectDay.value = date
    }

    fun updatePosition(date : LocalDate){
        position += ChronoUnit.MONTHS.between(liveSelectDay.value,date).toInt()
        setSelectDay(date)
    }

    fun getMonthSchedule(startDate: LocalDate , endDate: LocalDate){
        viewModelScope.launch(Dispatchers.IO) {
            getMonthScheduleUseCase(startDate, endDate).collectLatest {
                if (pastDay.year != liveSelectDay.value?.year && pastDay.monthValue != liveSelectDay.value?.monthValue)
                    cancel()
                when(it){
                    is Result.Success<*> ->{
                        val calender = it.successOrNull()
                        mutableMonthSchedule.emit(calender!!)
                    }
                    else -> mutableMonthSchedule.emit(Calender())
                }
            }
        }
    }
}
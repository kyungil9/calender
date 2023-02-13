package com.calender.presentation.data.viewmodels

import android.app.Application
import android.util.TypedValue
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
    application: Application,
    private val getSearchScheduleUseCase: GetSearchScheduleUseCase
    ) :ViewModel(){
    private val mutableHeight = MutableLiveData<Int>()
    var parentHeight = 0
    val liveHeight : LiveData<Int> get() = mutableHeight
    val scheduleResult : StateFlow<Result<List<Schedule>>> = getSearchScheduleUseCase(LocalDate.now())
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = Result.Loading
        )
    var mode = 0

    init {
        mutableHeight.value = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,50F,application.resources.displayMetrics).toInt()
    }

    fun setViewHeight(height : Int){
        mutableHeight.value = height / 5
    }

}
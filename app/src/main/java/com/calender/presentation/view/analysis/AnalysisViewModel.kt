package com.calender.presentation.view.analysis

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.calender.domain.model.Record
import com.calender.domain.model.Result
import com.calender.domain.model.successOrNull
import com.calender.domain.usecase.record.GetTodayRecordUseCase
import com.github.mikephil.charting.components.Description
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class AnalysisViewModel @Inject constructor(
    private val getTodayRecordUseCase: GetTodayRecordUseCase
) : ViewModel() {
    private val mutableDayRecord = MutableStateFlow(listOf<Record>())
    private val mutableToday = MutableLiveData<LocalDate>(LocalDate.now())

    val liveDayRecord = mutableDayRecord.asStateFlow()
    val liveToday : LiveData<LocalDate> get() = mutableToday
    val description = Description()
    private val pastDate: LocalDateTime = LocalDateTime.now()

    init {
        description.text = "오늘 일정"
        description.textSize = 15f
        getDayRecord(LocalDateTime.now())
    }

    fun getDayRecord(date: LocalDateTime){
        viewModelScope.launch(Dispatchers.IO) {
            getTodayRecordUseCase(date).collectLatest {
                if (pastDate.year != date.year && pastDate.monthValue != date.monthValue && pastDate.dayOfMonth != date.dayOfMonth)
                    cancel()
                when(it){
                    is Result.Success<*> -> mutableDayRecord.emit(it.successOrNull()!!)
                    else -> mutableDayRecord.emit(listOf())
                }
            }
        }
    }

    fun setToday(date: LocalDate){
        mutableToday.value = date
    }
}
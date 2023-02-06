package com.calender.presentation.data.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.calender.domain.model.Record
import com.calender.domain.model.Result
import com.calender.domain.model.successOrNull
import com.calender.domain.usecase.GetTodayRecordUseCase
import com.calender.domain.usecase.tag.GetRecordTagUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class RecordViewModel @Inject constructor(
    private val getTodayRecordUseCase: GetTodayRecordUseCase,
    private val getRecordTagUseCase: GetRecordTagUseCase
):ViewModel(){
    private val mutableSelectTag = MutableLiveData<String>()

    val liveTag : LiveData<String> get() = mutableSelectTag

    val recordResult : StateFlow<Result<List<Record>>> = getTodayRecordUseCase(date = LocalDate.now())
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = Result.Loading
        )

    val recordInfo : StateFlow<List<Record?>> = recordResult.mapLatest { state ->
        state.successOrNull() ?: emptyList<Record>()
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000L),
        initialValue = emptyList<Record>()
    )

    val recordTag : StateFlow<Result<List<String>>> = getRecordTagUseCase()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = Result.Loading
        )

    init {

    }
    fun selectTag(tag :String){
        mutableSelectTag.value = tag
    }

}
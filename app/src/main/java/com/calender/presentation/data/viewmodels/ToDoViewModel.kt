package com.calender.presentation.data.viewmodels

import android.content.Intent
import android.util.Log
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.calender.domain.model.Result
import com.calender.domain.model.ToDo
import com.calender.domain.model.ToDoCheck
import com.calender.domain.model.successOrNull
import com.calender.domain.usecase.GetAllToDoUseCase
import com.calender.domain.usecase.GetDateToDoUseCase
import com.calender.domain.usecase.GetProgramToDoUseCase
import com.calender.presentation.view.activity.AddToDo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class ToDoViewModel @Inject constructor(
    private val getProgramToDoUseCase: GetProgramToDoUseCase
):ViewModel(){
    val programResult : StateFlow<Result<List<ToDo>>> = getProgramToDoUseCase()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = Result.Loading
        )


//    val programInfo : StateFlow<List<ToDo>> = programResult.mapLatest { state ->
//        state.successOrNull() ?: emptyList<ToDo>()
//    }.stateIn(
//        scope = viewModelScope,
//        started = SharingStarted.WhileSubscribed(5_000L),
//        initialValue = emptyList<ToDo>()
//    )


}
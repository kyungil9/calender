package com.calender.presentation.data.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.calender.domain.model.Result
import com.calender.domain.model.ToDo
import com.calender.domain.usecase.todo.GetDateToDoUseCase
import com.calender.domain.usecase.todo.UpdateToDoStatePercentUseCase
import com.calender.domain.usecase.todo.UpdateToDoStateUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ToDoModeViewModel @Inject constructor(
    private val getDateToDoUseCase: GetDateToDoUseCase,
    private val updateToDoStateUseCase: UpdateToDoStateUseCase,
    private val updateToDoStatePercentUseCase: UpdateToDoStatePercentUseCase
):ViewModel(){
    val dateResult : StateFlow<Result<List<ToDo>>> = getDateToDoUseCase()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = Result.Loading
        )

    fun updateToDoState(state : Int,id:Int){
        viewModelScope.launch(Dispatchers.IO) {
            updateToDoStateUseCase(state, id)
        }
    }

    fun updateToDoStatePercent(statePercent : Int,id:Int){
        viewModelScope.launch(Dispatchers.IO) {
            updateToDoStatePercentUseCase(statePercent, id)
        }
    }
}
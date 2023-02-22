package com.calender.presentation.view.todo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.calender.domain.model.Result
import com.calender.domain.model.ToDo
import com.calender.domain.usecase.todo.GetProgramToDoUseCase
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
class ToDoViewModel @Inject constructor(
    private val getProgramToDoUseCase: GetProgramToDoUseCase,
    private val updateToDoStateUseCase: UpdateToDoStateUseCase,
    private val updateToDoStatePercentUseCase: UpdateToDoStatePercentUseCase
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
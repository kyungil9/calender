package com.calender.presentation.view.memo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.calender.domain.model.Memo
import com.calender.domain.model.Result
import com.calender.domain.model.successOrNull
import com.calender.domain.usecase.GetAllMemoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class MemoViewModel @Inject constructor(
    private val getAllMemoUseCase: GetAllMemoUseCase
) : ViewModel(){
    val memoResult : StateFlow<Result<List<Memo>>> = getAllMemoUseCase()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = Result.Loading
        )

}
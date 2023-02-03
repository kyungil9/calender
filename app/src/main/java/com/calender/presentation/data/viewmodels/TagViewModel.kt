package com.calender.presentation.data.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.calender.domain.model.Result
import com.calender.domain.usecase.tag.GetTagUseCase
import com.calender.domain.usecase.tag.InsertTagUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TagViewModel @Inject constructor(
    private val getTagUseCase : GetTagUseCase,
    private val insertTagUseCase: InsertTagUseCase
) : ViewModel(){
    val tagResult : StateFlow<Result<List<String>>> = getTagUseCase()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = Result.Loading
        )

    fun insertTag(tag : String){
        viewModelScope.launch(Dispatchers.IO) {
            insertTagUseCase(tag)
        }
    }

    var selectTag = ""

}
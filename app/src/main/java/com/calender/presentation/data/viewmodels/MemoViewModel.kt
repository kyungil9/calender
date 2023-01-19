package com.calender.presentation.data.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.calender.domain.model.Memo
import com.calender.domain.usecase.GetAllMemoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MemoViewModel @Inject constructor(
    private val getAllMemoUseCase: GetAllMemoUseCase
) : ViewModel(){
    private val mutableMemoInfo = MutableLiveData<ArrayList<Memo>>()
    val memoInfo : LiveData<ArrayList<Memo>> get() = mutableMemoInfo

}
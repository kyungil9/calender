package com.calender.presentation.data.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.calender.domain.model.Record
import com.calender.domain.usecase.GetTodayRecordUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RecordViewModel @Inject constructor(
    private val getTodayRecordUseCase: GetTodayRecordUseCase
):ViewModel(){
    private val mutableMemoInfo = MutableLiveData<ArrayList<Record>>()

    val memoInfo:LiveData<ArrayList<Record>> get() = mutableMemoInfo

}
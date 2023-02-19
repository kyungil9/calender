package com.calender.presentation.data.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CalenderAddViewModel @Inject constructor(

): ViewModel(){
    private val mutableTag = MutableLiveData<String>()

    val inputCalender = MutableLiveData<String>()
    val liveTag : LiveData<String> get() = mutableTag
    val repeatTags = listOf<String>("매일","매주","매월","매년")

    init {
        mutableTag.value =""
    }
}
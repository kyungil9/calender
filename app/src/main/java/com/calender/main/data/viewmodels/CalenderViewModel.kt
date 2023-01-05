package com.calender.main.data.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel

@HiltViewModel
class CalenderViewModel {
    private val mutableCalenderInfo = MutableLiveData<ArrayList<String>>()
    val calenderInfo:LiveData<ArrayList<String>> get() = mutableCalenderInfo

}
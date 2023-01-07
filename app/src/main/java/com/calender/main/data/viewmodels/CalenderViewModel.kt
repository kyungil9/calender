package com.calender.main.data.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.calender.main.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CalenderViewModel @Inject constructor(private val repository : Repository) :ViewModel(){
    private val mutableCalenderInfo = MutableLiveData<ArrayList<String>>()
    val calenderInfo:LiveData<ArrayList<String>> get() = mutableCalenderInfo

}
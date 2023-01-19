package com.calender.presentation.data.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.calender.domain.model.ToDoCheck
import com.calender.domain.usecase.GetAllToDoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ToDoViewModel @Inject constructor(
    private val getAllToDoUseCase: GetAllToDoUseCase
):ViewModel(){
    private val mutableToDoInfo = MutableLiveData<ArrayList<ToDoCheck>>()

    val toDoInfo:LiveData<ArrayList<ToDoCheck>> get() = mutableToDoInfo


}
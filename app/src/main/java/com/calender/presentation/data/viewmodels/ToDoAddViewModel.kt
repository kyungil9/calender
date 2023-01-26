package com.calender.presentation.data.viewmodels

import androidx.lifecycle.ViewModel
import com.calender.presentation.utils.NumberPick
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ToDoAddViewModel @Inject constructor(

) : ViewModel() {
    val np = NumberPick()
}
package com.calender.presentation.view.addMemo

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.calender.domain.model.Memo
import com.calender.domain.usecase.memo.DelectMemoUseCase
import com.calender.domain.usecase.memo.InsertMemoUseCase
import com.calender.domain.usecase.memo.UpdateMemoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MemoAddViewModel @Inject constructor(
    private val insertMemoUseCase: InsertMemoUseCase,
    private val updateMemoUseCase: UpdateMemoUseCase,
    private val delectMemoUseCase: DelectMemoUseCase
) : ViewModel(){
    val inputTitle = MutableLiveData<String>("")
    val inputDetail = MutableLiveData<String>("")
    var newMemo = true
    var check = true
    private var memo : Memo? = null

    fun setUpdateMemo(memo: Memo){
        this.memo = memo
        inputTitle.value = memo.title
        inputDetail.value = memo.detail
    }

    fun insertMemo(){
        viewModelScope.launch(Dispatchers.IO) {
            insertMemoUseCase(Memo(0,inputTitle.value!!,inputDetail.value!!,""))
        }
        newMemo = false
    }
    fun updateMemo(){
        viewModelScope.launch(Dispatchers.IO){
            memo?.title = inputTitle.value!!
            memo?.detail = inputDetail.value!!
            updateMemoUseCase(memo!!)
        }
    }
    fun delectMemo(){
        viewModelScope.launch(Dispatchers.IO) {
            delectMemoUseCase(memo!!)
        }
    }

}
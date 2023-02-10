package com.calender.presentation.data.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.calender.domain.model.Record
import com.calender.domain.model.Result
import com.calender.domain.model.ToDo
import com.calender.domain.model.successOrNull
import com.calender.domain.usecase.record.GetSelectRecordUseCase
import com.calender.domain.usecase.record.GetTodayRecordUseCase
import com.calender.domain.usecase.record.InsertRecordUseCase
import com.calender.domain.usecase.record.UpdateRecordUseCase
import com.calender.domain.usecase.tag.GetRecordTagUseCase
import com.calender.domain.usecase.tag.InsertTagUseCase
import com.calender.domain.usecase.todo.GetOneDateToDoUseCase
import com.calender.domain.usecase.todo.UpdateToDoStatePercentUseCase
import com.calender.domain.usecase.todo.UpdateToDoStateUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.time.Duration
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.Timer
import java.util.TimerTask
import javax.inject.Inject

@HiltViewModel
class RecordViewModel @Inject constructor(
    private val getTodayRecordUseCase: GetTodayRecordUseCase,
    private val getRecordTagUseCase: GetRecordTagUseCase,
    private val updateRecordUseCase: UpdateRecordUseCase,
    private val insertRecordUseCase: InsertRecordUseCase,
    private val getSelectRecordUseCase: GetSelectRecordUseCase,
    private val updateToDoStatePercentUseCase: UpdateToDoStatePercentUseCase,
    private val updateToDoStateUseCase: UpdateToDoStateUseCase,
    private val getOneDateToDoUseCase: GetOneDateToDoUseCase,
    private val insertTagUseCase: InsertTagUseCase
):ViewModel(){

    private val mutableProgressTime = MutableLiveData<Duration>()
    private val mutableTodayDate = MutableLiveData<LocalDate>()

    var fabMain_status = false
    val liveProgressTime : LiveData<Duration> get() = mutableProgressTime
    val liveTodayDate : LiveData<LocalDate> get() = mutableTodayDate
    val timer = Timer()
    val dumyToDo = ToDo(title = "-1234567")
    val recordResult : StateFlow<Result<List<Record>>> = getTodayRecordUseCase(date = LocalDateTime.now())
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = Result.Loading
        )

//    val recordInfo : StateFlow<List<Record?>> = recordResult.mapLatest { state ->
//        state.successOrNull() ?: emptyList<Record>()
//    }.stateIn(
//        scope = viewModelScope,
//        started = SharingStarted.WhileSubscribed(5_000L),
//        initialValue = emptyList<Record>()
//    )

    val recordTag : StateFlow<Result<List<String>>> = getRecordTagUseCase()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = Result.Loading
        )

    val selectRecord : StateFlow<Result<Record>> = getSelectRecordUseCase()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = Result.Loading
        )

    val todayToDo : StateFlow<Result<ToDo>> = getOneDateToDoUseCase(liveTodayDate)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = Result.Loading
        )


    init {
        //현재 선택되어있는 record 불러오기
        mutableTodayDate.value = LocalDate.now()
        mutableProgressTime.value = Duration.ZERO

    }


    fun insertRecord(tag : String){
        viewModelScope.launch(Dispatchers.IO){
            insertRecordUseCase(Record(
                id = 0,
                tag = tag,
                startTime = LocalDateTime.now(),
                endTime = null,
                progressTime = 0,
                check = true
            ))
        }
    }

    fun updateRecord(){
        val record = selectRecord.value.successOrNull()
        if (record != null){
            val time = LocalDateTime.now()
            val duration = Duration.between(record.startTime,time)
            viewModelScope.launch(Dispatchers.IO){
                updateRecordUseCase(time,duration.toMinutes(),record.id)
            }
        }
    }

    val timerTask = object : TimerTask(){
        override fun run() {
            val record = selectRecord.value.successOrNull()
            if (record != null) {
                val time = LocalDateTime.now()
                val duration = Duration.between(record.startTime, time)
                mutableProgressTime.postValue(duration)
                if (liveTodayDate.value?.dayOfMonth != time.dayOfMonth)
                    mutableTodayDate.postValue(time.toLocalDate())
            }
        }
    }
    fun updateToDoState(state : Int,id:Int){
        viewModelScope.launch(Dispatchers.IO) {
            updateToDoStateUseCase(state, id)
        }
    }

    fun updateToDoStatePercent(statePercent : Int,id:Int){
        viewModelScope.launch(Dispatchers.IO) {
            updateToDoStatePercentUseCase(statePercent, id)
        }
    }

    fun insertHomeTag(tag : String){
        viewModelScope.launch(Dispatchers.IO) {
            insertTagUseCase(tag,1)
        }
    }

}
package com.calender.presentation.view.activity

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.SeekBar
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.core.view.get
import androidx.navigation.findNavController
import com.calender.presentation.R
import com.calender.presentation.base.BaseActivity
import com.calender.presentation.data.viewmodels.ToDoAddViewModel
import com.calender.presentation.databinding.ActivityAddToDoBinding
import com.calender.presentation.databinding.ViewCalenderItemBinding
import com.calender.presentation.utils.NumberPick
import com.google.android.material.chip.Chip
import dagger.hilt.android.AndroidEntryPoint
import org.jetbrains.anko.startActivityForResult
import java.time.LocalDate


@AndroidEntryPoint
class AddToDo : BaseActivity<ActivityAddToDoBinding>(R.layout.activity_add_to_do,TransitionMode.VERTICAL) {
    private val viewModel : ToDoAddViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(binding.todoToolbar.toolbar)
        supportActionBar?.apply {
            setDisplayShowTitleEnabled(false)
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_baseline_clear_24)
        }
        val resultActivity = registerForActivityResult(ActivityResultContracts.StartActivityForResult(),
            ActivityResultCallback<ActivityResult> { result ->
                if (result.resultCode == RESULT_OK){
                    viewModel.setTag(result.data?.getStringExtra("tag")!!)
                    viewModel.tagIndex = result.data?.getIntExtra("index",0)!!
                }
            })

        binding.apply {
            lifecycleOwner = this@AddToDo
            todoToolbar.toolbarTitle.text = "할 일"
            vm = viewModel
            //start부분
            todoStartDateView.calenderView.npYear.setOnValueChangedListener { _, _, i2 ->
                viewModel.startNp.changeDayInfo(viewModel.startNp.setYear(i2))
                changeDays(binding.todoStartDateView,viewModel.startNp)
                viewModel.loadStartData()
                updateEndDate()
            }
            todoStartDateView.calenderView.npMonth.setOnValueChangedListener { _, _, i2 ->
                viewModel.startNp.changeDayInfo(viewModel.startNp.setMonth(i2))
                changeDays(binding.todoStartDateView,viewModel.startNp)
                viewModel.loadStartData()
                updateEndDate()
            }
            todoStartDateView.calenderView.npDay.setOnValueChangedListener { _, _, i2 ->
                viewModel.startNp.setDay(i2)
                viewModel.loadStartData()
                updateEndDate()
            }
            todoStartDateView.calenderSelectview.selectChipGroup.setOnCheckedStateChangeListener { group, checkedIds ->
                val id = group.checkedChipId
                if (id != View.NO_ID){
                    when(group.findViewById<Chip>(id).text){
                        "오늘" -> setNumberPickerDays(binding.todoStartDateView, LocalDate.now(),viewModel.startNp)
                        "내일" -> setNumberPickerDays(binding.todoStartDateView, LocalDate.now().plusDays(1L),viewModel.startNp)
                        "다음주" -> setNumberPickerDays(binding.todoStartDateView, LocalDate.now().plusWeeks(1L),viewModel.startNp)
                    }
                    viewModel.loadStartData()
                    updateEndDate()
                }
            }

            //end부분
            todoEndDateView.calenderView.npYear.setOnValueChangedListener { _, _, i2 ->
                if(viewModel.liveStartDate.value!! > viewModel.endNp.getToday().withYear(i2+2000)){
                    updateEndDate()
                }else {
                    viewModel.endNp.changeDayInfo(viewModel.endNp.setYear(i2))
                    changeDays(binding.todoEndDateView, viewModel.endNp)
                    viewModel.loadEndData()
                }
            }
            todoEndDateView.calenderView.npMonth.setOnValueChangedListener { _, _, i2 ->
                if(viewModel.liveStartDate.value!! > viewModel.endNp.getToday().withMonth(i2+1)){
                    updateEndDate()
                }else {
                    viewModel.endNp.changeDayInfo(viewModel.endNp.setMonth(i2))
                    changeDays(binding.todoEndDateView, viewModel.endNp)
                    viewModel.loadEndData()
                }
            }
            todoEndDateView.calenderView.npDay.setOnValueChangedListener { _, _, i2 ->
                if(viewModel.liveStartDate.value!! > viewModel.endNp.getToday().withDayOfMonth(i2+1)){
                    updateEndDate()
                }else {
                    viewModel.endNp.setDay(i2)
                    viewModel.loadEndData()
                }
            }
            todoEndDateView.calenderSelectview.selectChipGroup.setOnCheckedStateChangeListener { group, _ ->
                val id = group.checkedChipId
                if (id != View.NO_ID){
                    when(group.findViewById<Chip>(id).text){
                        "오늘" -> setNumberPickerDays(binding.todoEndDateView, viewModel.liveStartDate.value!!,viewModel.endNp)
                        "내일" -> setNumberPickerDays(binding.todoEndDateView, viewModel.liveStartDate.value!!.plusDays(1L),viewModel.endNp)
                        "다음주" -> setNumberPickerDays(binding.todoEndDateView, viewModel.liveStartDate.value!!.plusWeeks(1L),viewModel.endNp)
                        "다음달" -> setNumberPickerDays(binding.todoEndDateView, viewModel.liveStartDate.value!!.plusMonths(1L),viewModel.endNp)
                    }
                    viewModel.loadEndData()
                }
            }

            todoRepeatView.selectChipGroup.isSelectionRequired = false
            todoRepeatView.selectChipGroup.setOnCheckedStateChangeListener { group, _ ->
                val id = group.checkedChipId
                if (id == View.NO_ID){
                    viewModel.setRepeat("")
                }else{
                    val chip = group.findViewById<Chip>(id)
                    viewModel.setRepeat(chip.text.toString())
                }
            }
            todoStateView.selectChipGroup.setOnCheckedStateChangeListener { group, _ ->
                val id = group.checkedChipId
                if (id == View.NO_ID){
                    viewModel.setState("")
                    todoStateProcessing.viewSeekbar.visibility = View.GONE
                }else{
                    val chip = group.findViewById<Chip>(id)
                    viewModel.setState(chip.text.toString())
                    if (chip.text.toString() == "진행중")
                        todoStateProcessing.viewSeekbar.visibility = View.VISIBLE
                    else
                        todoStateProcessing.viewSeekbar.visibility = View.GONE
                }
            }

            todoStateProcessing.seekbarTodo.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
                override fun onStartTrackingTouch(p0: SeekBar?) {
                }
                override fun onStopTrackingTouch(p0: SeekBar?) {
                }
                override fun onProgressChanged(p0: SeekBar?, progress: Int, p2: Boolean) {
                    viewModel.setStateProcessing(progress * 5)
                }
            })

            todoTagsView.selectItemView.setOnClickListener {
                val intent = Intent(application,AddTag::class.java)
                intent.putExtra("defaultTag",viewModel.liveTag.value.toString())
                intent.putExtra("defaultIndex",viewModel.tagIndex)
                resultActivity.launch(intent)
            }
        }
    }

    fun setNumberPickerDays(binding : ViewCalenderItemBinding,date : LocalDate,np : NumberPick){
        np.changeDayInfo(np.setToday(date))
        binding.calenderView.npYear.value = np.getYearCurrent()
        binding.calenderView.npMonth.value = np.getMonthCurrent()
        changeDays(binding,np)
        binding.calenderView.npDay.value = np.getDayCurrent()
    }

    fun changeDays(binding : ViewCalenderItemBinding,np : NumberPick){
        binding.calenderView.npDay.apply {
            displayedValues = null
            maxValue = np.getDaySize()
            displayedValues = np.getDayValue()
        }
    }

    fun updateEndDate(){
        setNumberPickerDays(binding.todoEndDateView, viewModel.liveStartDate.value!!,viewModel.endNp)
        viewModel.loadEndData()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.check_menu,menu)
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            android.R.id.home -> {
                finish()
                true
            }

            R.id.register -> {
                //입력된 데이터를 저장하는 코드와 함께 종료
                viewModel.registerToDo()
                finish()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

}
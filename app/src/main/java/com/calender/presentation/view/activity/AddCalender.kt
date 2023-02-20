package com.calender.presentation.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import com.borax12.materialdaterangepicker.time.TimePickerDialog
import com.calender.presentation.R
import com.calender.presentation.base.BaseActivity
import com.calender.presentation.data.viewmodels.CalenderAddViewModel
import com.calender.presentation.databinding.ActivityAddCalenderBinding
import com.calender.presentation.utils.CalenderUtils
import com.google.android.material.chip.Chip
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDate
import java.time.LocalTime

@AndroidEntryPoint
class AddCalender : BaseActivity<ActivityAddCalenderBinding>(R.layout.activity_add_calender,TransitionMode.VERTICAL) {
    private val viewModel : CalenderAddViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(binding.calenderToolbar.toolbar)
        val selectDay = intent.getSerializableExtra("selectDay") as LocalDate
        viewModel.schedule.date = selectDay
        supportActionBar?.apply {
            setDisplayShowTitleEnabled(false)
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_baseline_clear_24)
        }
        binding.apply {
            lifecycleOwner = this@AddCalender
            vm = viewModel
            calenderToolbar.toolbarTitle.text = "${selectDay.year}.${selectDay.monthValue}.${selectDay.dayOfMonth}(${CalenderUtils.transDayToKorean(selectDay.dayOfWeek.value)})"
            calenderRepeatView.selectChipGroup.apply {
                isSelectionRequired = false
                setOnCheckedStateChangeListener { group, _ ->
                    val id = group.checkedChipId
                    if (id == View.NO_ID){
                        viewModel.setRepeat("")
                    }else{
                        val chip = group.findViewById<Chip>(id)
                        viewModel.setRepeat(chip.text.toString())
                    }
                }
            }
            calenderTimeView.selectItemView.setOnClickListener {
                val time = LocalTime.now()
                val timePickerDialog = TimePickerDialog.newInstance(
                    { view,startHour,startMinute,endHour,endMinute ->
                        viewModel.setTimePeriod(LocalTime.of(startHour,startMinute,0),LocalTime.of(endHour,endMinute,0))
                        calenderTimeView.itemText.text = "시작시간 : ${startHour}:${startMinute}\n종료시간 : ${endHour}:${endMinute}"
                    },
                    time.hour,
                    time.minute,
                    false
                )
                timePickerDialog.show(fragmentManager,"시간 지정")
            }
            calenderTimeView.clearButton.apply {
                visibility = View.VISIBLE
                setOnClickListener {
                    viewModel.clearTime()
                    calenderTimeView.itemText.text = "종일"
                }
            }
        }
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
                viewModel.registerSchedule()
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
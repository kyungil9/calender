package com.calender.presentation.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.calender.presentation.R
import com.calender.presentation.base.BaseActivity
import com.calender.presentation.databinding.ActivityAddCalenderBinding
import com.calender.presentation.utils.CalenderUtils
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDate
@AndroidEntryPoint
class AddCalender : BaseActivity<ActivityAddCalenderBinding>(R.layout.activity_add_calender,TransitionMode.VERTICAL) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(binding.calenderToolbar.toolbar)
        val selectDay = intent.getSerializableExtra("selectDay") as LocalDate
        supportActionBar?.apply {
            setDisplayShowTitleEnabled(false)
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_baseline_clear_24)
        }
        binding.apply {
            calenderToolbar.toolbarTitle.text = "${selectDay.year}.${selectDay.monthValue}.${selectDay.dayOfMonth}(${CalenderUtils.transDayToKorean(selectDay.dayOfWeek.value)})"
            calenderRepeatView.selectChipGroup.isSelectionRequired = false

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

                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
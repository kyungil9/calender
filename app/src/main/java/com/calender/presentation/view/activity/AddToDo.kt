package com.calender.presentation.view.activity

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.navigation.findNavController
import com.calender.presentation.R
import com.calender.presentation.base.BaseActivity
import com.calender.presentation.data.viewmodels.ToDoAddViewModel
import com.calender.presentation.databinding.ActivityAddToDoBinding
import dagger.hilt.android.AndroidEntryPoint

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

        binding.apply {
            todoToolbar.toolbarTitle.text = "할 일"
            vm = viewModel
            todoStartDateView.calenderView.npYear.setOnValueChangedListener { numberPicker, i, i2 ->
                viewModel.startNp.changeDayInfo(viewModel.startNp.setYear(i2))
                todoStartDateView.calenderView.npDay.apply {
                    displayedValues = null
                    maxValue = viewModel.startNp.getDaySize()
                    displayedValues = viewModel.startNp.getDayValue()
                }
                viewModel.loadStartData()
            }
            todoStartDateView.calenderView.npMonth.setOnValueChangedListener { numberPicker, i, i2 ->
                viewModel.startNp.changeDayInfo(viewModel.startNp.setMonth(i2))
                todoStartDateView.calenderView.npDay.apply {
                    displayedValues = null
                    maxValue = viewModel.startNp.getDaySize()
                    displayedValues = viewModel.startNp.getDayValue()
                }
                viewModel.loadStartData()
            }
            todoStartDateView.calenderView.npDay.setOnValueChangedListener { numberPicker, i, i2 ->
                viewModel.startNp.setDay(i2)
                viewModel.loadStartData()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.check_menu,menu)
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home -> {
                finish()
                return true
            }
            R.id.register -> {
                //입력된 데이터를 저장하는 코드와 함께 종료
                finish()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

}
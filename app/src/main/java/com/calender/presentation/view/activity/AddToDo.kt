package com.calender.presentation.view.activity

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
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
            todoCalenderView.npYear.setOnValueChangedListener { numberPicker, i, i2 ->
                viewModel.np.changeDayInfo(viewModel.np.setYear(i2))
                todoCalenderView.npDay.apply {
                    displayedValues = null
                    maxValue = viewModel.np.getDaySize()
                    displayedValues = viewModel.np.getDayValue()
                }
            }
            todoCalenderView.npMonth.setOnValueChangedListener { numberPicker, i, i2 ->
                viewModel.np.changeDayInfo(viewModel.np.setMonth(i2))
                todoCalenderView.npDay.apply {
                    displayedValues = null
                    maxValue = viewModel.np.getDaySize()
                    displayedValues = viewModel.np.getDayValue()
                }
            }
            todoCalenderView.npDay.setOnValueChangedListener { numberPicker, i, i2 ->
                viewModel.np.setDay(i2)
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
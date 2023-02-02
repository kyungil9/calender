package com.calender.presentation.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.calender.presentation.R
import com.calender.presentation.base.BaseActivity
import com.calender.presentation.databinding.ActivityAddTagBinding

class AddTag : BaseActivity<ActivityAddTagBinding>(R.layout.activity_add_tag,TransitionMode.HORIZON) {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(binding.todoToolbar.toolbar)
        supportActionBar?.apply {
            setDisplayShowTitleEnabled(false)
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_24)
        }
    }



    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.tag_menu,menu)
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            android.R.id.home -> {
                finish()
                true
            }
            R.id.add_tag -> {
                //다이얼로그 띄우기
                true
            }
            R.id.register_tag -> {
                //입력된 데이터를 저장하는 코드와 함께 종료
                finish()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
}
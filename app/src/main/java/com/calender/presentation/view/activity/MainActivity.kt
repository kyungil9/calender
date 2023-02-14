package com.calender.presentation.view.activity

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.calender.presentation.R


import com.calender.presentation.base.BaseActivity
import com.calender.presentation.databinding.ActivityMainBinding
import com.calender.presentation.utils.ForceService
import com.calender.presentation.utils.KeepStateNavigator
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDate

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main,TransitionMode.NONE) {
    private var backKeyPressedTime = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startService(Intent(this, ForceService::class.java))
        val navMainFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment//바텀네비게이터 설정
        val navController = navMainFragment.navController
        val navigator = KeepStateNavigator(this,navMainFragment.childFragmentManager,R.id.fragmentContainerView)
        navController.navigatorProvider.addNavigator(navigator)
        navController.setGraph(R.navigation.main_nav_graph)
        binding.mainBottomNav.setupWithNavController(navController)
        setSupportActionBar(binding.toolbar.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }


    override fun onBackPressed() {//뒤로가기를 막고 빠르게 더블 터치해야 화면이 닫힘,로그인 창으로 넘어가는것을 방지
        if(System.currentTimeMillis() > backKeyPressedTime + 2500){
            backKeyPressedTime = System.currentTimeMillis()
            return
        }else{
            finishAffinity()
        }
    }

    fun setActionBarTitle(title: String) {
        binding.toolbar.toolbarTitle.text = title
    }

}
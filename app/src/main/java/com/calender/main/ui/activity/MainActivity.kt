package com.calender.main.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.calender.main.R
import com.calender.main.custom_toast
import com.calender.main.databinding.ActivityMainBinding
import com.calender.main.security
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private var backKeyPressedTime = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val navMainFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment//바텀네비게이터 설정
        val navController = navMainFragment.navController
        binding.mainBottomNav.setupWithNavController(navController)
    }






    override fun onResume() {
        super.onResume()
        if(security.isRooting()){
            custom_toast.shortToast(this, "루팅된 os는 실행이 불가합니다.")
            finish()
        }
    }

    override fun onBackPressed() {//뒤로가기를 막고 빠르게 더블 터치해야 화면이 닫힘,로그인 창으로 넘어가는것을 방지
        if(System.currentTimeMillis() > backKeyPressedTime + 2500){
            backKeyPressedTime = System.currentTimeMillis()
            return
        }else{
            finishAffinity()
        }
    }
}
package com.calender.presentation.base

import android.content.Intent
import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.calender.presentation.R
import com.calender.presentation.custom_toast
import com.calender.presentation.security
import com.calender.presentation.utils.ForceService


abstract class BaseActivity<T : ViewDataBinding>(
    @LayoutRes val layoutId : Int,
    private val transitionMode : TransitionMode
) :AppCompatActivity(){

    private var _binding : T ?= null
    val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = DataBindingUtil.setContentView(this, layoutId)
        binding.lifecycleOwner = this

        when(transitionMode){
            TransitionMode.VERTICAL -> overridePendingTransition(R.anim.vertical_enter, R.anim.none)
            else -> Unit
        }
    }

    override fun onResume() {
        super.onResume()
        if(security.isRooting()){
            custom_toast.shortToast(this, "루팅된 os는 실행이 불가합니다.")
            finish()
        }
    }

    override fun finish() {
        super.finish()
        when(transitionMode){
            TransitionMode.VERTICAL -> overridePendingTransition(R.anim.none,R.anim.vertical_exit)
            else -> Unit
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        when(transitionMode){
            TransitionMode.VERTICAL -> overridePendingTransition(R.anim.none,R.anim.vertical_exit)
            else -> Unit
        }
    }

    enum class TransitionMode{
        NONE,
        VERTICAL
    }
}
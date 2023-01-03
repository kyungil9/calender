package com.calender.main

import android.content.Context
import android.os.Handler
import android.widget.Toast

class custom_toast {
    companion object{
        var toast: Toast? = null
        fun shortToast(ctx: Context, s:String){
            toast?.cancel()
            toast = Toast.makeText(ctx,s, Toast.LENGTH_SHORT)
            toast?.show()
            Handler().postDelayed({
                toast?.cancel()
            }, 1000L)
        }
        fun vsToast(ctx: Context, s: String){
            toast?.cancel()
            toast = Toast.makeText(ctx,s, Toast.LENGTH_SHORT)
            toast?.show()
            Handler().postDelayed({
                toast?.cancel()
            }, 500L)
        }
    }
}
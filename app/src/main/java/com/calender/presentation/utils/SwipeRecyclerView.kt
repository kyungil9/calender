package com.calender.presentation.utils

import android.content.Context
import android.util.AttributeSet
import android.view.GestureDetector
import android.view.MotionEvent
import androidx.recyclerview.widget.RecyclerView
import java.lang.Exception

class SwipeRecyclerView @JvmOverloads constructor(
    context : Context,
    attrs : AttributeSet? = null,
    defStyleAttr : Int =0
) : RecyclerView(context, attrs, defStyleAttr) {
    private var gesture : GestureDetector? = null

    fun setGestureDetector(g : GestureDetector){
        gesture = g
    }

    override fun onTouchEvent(ev: MotionEvent?): Boolean {
        try {
            return gesture?.onTouchEvent(ev)!!
        }catch (exception: Exception){
            exception.printStackTrace()
        }
        return false
    }
}
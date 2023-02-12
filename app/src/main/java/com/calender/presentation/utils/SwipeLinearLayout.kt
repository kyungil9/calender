package com.calender.presentation.utils

import android.content.Context
import android.util.AttributeSet
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet.Constraint
import androidx.core.view.MotionEventCompat
import com.calender.presentation.databinding.FragmentCalenderBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import java.lang.Exception
import kotlin.math.abs

class SwipeLinearLayout @JvmOverloads constructor(
    context : Context,
    attrs : AttributeSet? = null,
    defStyleAttr : Int =0
) : LinearLayoutCompat(context, attrs, defStyleAttr){
    private val gestureDetector : GestureDetector
    //private var binding : FragmentCalenderBinding? = null
    private var bottomBehavior :BottomSheetBehavior<ConstraintLayout>? = null
    private var lastY = 0
    private val OFF_SET = 10
    companion object{
        private val SWIPE_THRESHOLD = 100
        private val SWIPE_VELOCITY_THRESHOLD = 100
    }
    init {
        gestureDetector = GestureDetector(context,GestureListener())
    }

//    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
//        try {
//            return gestureDetector.onTouchEvent(ev)
//        }catch (exception:Exception){
//            exception.printStackTrace()
//        }
//        return false

//        val action = ev?.action
//        when(action){
//            MotionEvent.ACTION_DOWN -> lastY = ev.y.toInt()
//            MotionEvent.ACTION_MOVE -> {
//                var curY = ev.y.toInt()
//                return !(curY - OFF_SET <= lastY && lastY <= curY + OFF_SET)
//            }
//        }
//        return false
//    }

    override fun onTouchEvent(ev: MotionEvent?): Boolean {
        try {
            return gestureDetector.onTouchEvent(ev)
        }catch (exception:Exception){
            exception.printStackTrace()
        }
        return false
    }

    private inner class GestureListener : GestureDetector.SimpleOnGestureListener(){

        override fun onDown(e: MotionEvent): Boolean {
            return true
        }

        override fun onFling(e1: MotionEvent?, e2: MotionEvent?, velocityX: Float, velocityY: Float): Boolean {
            var result = false
            try {
                val diffY = e2!!.y - e1!!.y
                val diffX = e2.x - e1.x
                if (abs(diffX) > abs(diffY)) {
                    if (abs(diffX) > SWIPE_THRESHOLD && abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                        if (diffX > 0) {
                            onSwipeRight()
                        } else {
                            onSwipeLeft()
                        }
                        result = false
                    }
                } else if (abs(diffY) > SWIPE_THRESHOLD && abs(velocityY) > SWIPE_VELOCITY_THRESHOLD) {
                    if (diffY < 0) {
                        onSwipeBottom()
                    } else {
                        onSwipeTop()
                    }
                    result = true
                }
            } catch (exception: Exception) {
                exception.printStackTrace()
            }
            return result
        }
    }//bottomsheetbehavior로 변경해서 기존 작성한거 전부 삭제

    open fun onSwipeBottom() {
        //binding?.calenderLayout?.visibility = View.GONE
        bottomBehavior?.state = if (bottomBehavior?.state == BottomSheetBehavior.STATE_EXPANDED){
            BottomSheetBehavior.STATE_HALF_EXPANDED
        }else
            BottomSheetBehavior.STATE_EXPANDED
    }

    open fun onSwipeLeft() {}

    open fun onSwipeRight() {}

    open fun onSwipeTop() {
        bottomBehavior?.state = if (bottomBehavior?.state == BottomSheetBehavior.STATE_HALF_EXPANDED){
            BottomSheetBehavior.STATE_EXPANDED
        }else
            BottomSheetBehavior.STATE_HALF_EXPANDED
        //binding?.calenderLayout?.visibility = View.VISIBLE
    }

    fun setBinding(b : BottomSheetBehavior<ConstraintLayout>){
        bottomBehavior = b
       // binding?.customCalender?.setGestureDetector(gestureDetector)
    }
}
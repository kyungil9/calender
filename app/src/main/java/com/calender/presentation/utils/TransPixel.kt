package com.calender.presentation.utils

import android.content.Context
import android.util.TypedValue

fun getPixelToDp(context : Context,pixel : Int) : Int{
    val metrics = context.resources.displayMetrics
    val dp = pixel / (metrics.densityDpi/160F)
   return dp.toInt()
}

fun getDpToPixel(context: Context,dp : Float) : Int{
    val px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,dp,context.resources.displayMetrics)
    return px.toInt()
}
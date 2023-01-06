package com.calender.main.ui.base

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class HorizonItemDecorator (private val divHeight : Int) : RecyclerView.ItemDecoration() {

    @Override
    override fun getItemOffsets(outRect: Rect, view: View, parent : RecyclerView, state : RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect.left = divHeight
        outRect.right = divHeight
    }
}
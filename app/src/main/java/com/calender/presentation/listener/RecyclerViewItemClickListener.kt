package com.calender.presentation.listener

import android.view.View
import java.time.LocalDate

interface RecyclerViewItemClickListener {
    fun onItemClickListener(item : LocalDate,view : View)

}
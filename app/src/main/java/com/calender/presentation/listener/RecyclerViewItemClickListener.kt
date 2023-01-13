package com.calender.presentation.listener

import java.time.LocalDate

interface RecyclerViewItemClickListener {
    abstract fun onItemClickListener(date : LocalDate)
}
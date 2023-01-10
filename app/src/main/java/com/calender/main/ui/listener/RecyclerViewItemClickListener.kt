package com.calender.main.ui.listener

import java.time.LocalDate

interface RecyclerViewItemClickListener {
    abstract fun onItemClickListener(date : LocalDate)
}
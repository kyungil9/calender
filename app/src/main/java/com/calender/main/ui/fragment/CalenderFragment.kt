package com.calender.main.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import com.calender.main.R
import com.calender.main.databinding.FragmentCalenderBinding
import com.calender.main.ui.adapter.MonthAdapter
import com.calender.main.ui.base.BindFragment
import com.calender.main.ui.base.HorizonItemDecorator
import dagger.hilt.android.AndroidEntryPoint
import java.time.Month
import javax.inject.Inject

@AndroidEntryPoint
class CalenderFragment : BindFragment<FragmentCalenderBinding>(R.layout.fragment_calender) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val monthManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
        monthManager.isAutoMeasureEnabled = false
        binding.customCalender.apply {
            layoutManager = monthManager
            adapter = MonthAdapter()
            addItemDecoration(HorizonItemDecorator(10))
            scrollToPosition(Int.MAX_VALUE/2)
            setHasFixedSize(true)
        }
        val sanp = PagerSnapHelper()
        sanp.attachToRecyclerView(binding.customCalender)//달별로 페이지 넘기기
    }



}
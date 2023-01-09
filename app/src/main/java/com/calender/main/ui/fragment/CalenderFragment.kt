package com.calender.main.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import com.calender.main.R
import com.calender.main.data.entity.Schedule
import com.calender.main.data.viewmodels.CalenderViewModel
import com.calender.main.databinding.FragmentCalenderBinding
import com.calender.main.ui.adapter.CalenderAdapter
import com.calender.main.ui.adapter.ScheduleAdapter
import com.calender.main.ui.base.BaseFragment
import com.calender.main.ui.base.HorizonItemDecorator
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDate
import java.time.LocalTime

@AndroidEntryPoint
class CalenderFragment : BaseFragment<FragmentCalenderBinding>(R.layout.fragment_calender) {
    private val calenderViewModel : CalenderViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val monthManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
        binding.customCalender.apply {
            layoutManager = monthManager
            adapter = CalenderAdapter()
            addItemDecoration(HorizonItemDecorator(10))
            scrollToPosition(Int.MAX_VALUE/2)
            setHasFixedSize(true)
        }
        val sanp = PagerSnapHelper()
        sanp.attachToRecyclerView(binding.customCalender)//달별로 페이지 넘기기


        val dumy = ArrayList<Schedule>()
        dumy.add(Schedule(LocalDate.now(), LocalTime.now(),"test1"))
        dumy.add(Schedule(LocalDate.now(), LocalTime.now(),"test2"))
        val scheduleAdapter = ScheduleAdapter()
        val scheduleManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
        binding.scheduleList.apply {
            layoutManager = scheduleManager
            adapter = scheduleAdapter
            setHasFixedSize(true)
        }
        scheduleAdapter.setItems(dumy)
    }
}
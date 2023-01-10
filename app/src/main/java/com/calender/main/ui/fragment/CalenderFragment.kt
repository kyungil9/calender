package com.calender.main.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import com.calender.main.R
import com.calender.main.data.viewmodels.CalenderViewModel
import com.calender.main.databinding.FragmentCalenderBinding
import com.calender.main.ui.adapter.CalenderAdapter
import com.calender.main.ui.adapter.ScheduleAdapter
import com.calender.main.ui.base.BaseFragment
import com.calender.main.ui.base.HorizonItemDecorator
import com.calender.main.ui.listener.RecyclerViewItemClickListener
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDate

@AndroidEntryPoint
class CalenderFragment : BaseFragment<FragmentCalenderBinding>(R.layout.fragment_calender) {
    private val calenderViewModel : CalenderViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val calenderAdapter = CalenderAdapter()
        val monthManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
        binding.customCalender.apply {
            layoutManager = monthManager
            adapter = calenderAdapter
            addItemDecoration(HorizonItemDecorator(10))
            scrollToPosition(Int.MAX_VALUE/2)
            setHasFixedSize(true)
        }
        val sanp = PagerSnapHelper()
        sanp.attachToRecyclerView(binding.customCalender)//달별로 페이지 넘기기


        val scheduleAdapter = ScheduleAdapter()
        val scheduleManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
        binding.scheduleList.apply {
            layoutManager = scheduleManager
            adapter = scheduleAdapter
            setHasFixedSize(true)
        }
        calenderViewModel.searchScheduleDate(LocalDate.now())//오늘 날짜에 저장된 정보를 띄워줌

        calenderAdapter.setOnItemClickListener(object : RecyclerViewItemClickListener{
            override fun onItemClickListener(date: LocalDate) {//해당 날짜의 데이터 조회해서 입력
                calenderViewModel.searchScheduleDate(date)//room에서 해당 날짜 데이터 받아와서 viewmodel에 저장
            }
        })

        calenderViewModel.scheduleInfo.observe(viewLifecycleOwner, Observer {
            scheduleAdapter.setItems(it)//다른 날짜 클릭시 해당날짜에 저장된 데이터 불러와서 업데이트
        })



    }
}
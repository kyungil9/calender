package com.calender.presentation.view.fragment

import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.view.*
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import com.calender.presentation.R
import com.calender.presentation.data.viewmodels.CalenderViewModel
import com.calender.presentation.view.adapter.CalenderAdapter
import com.calender.presentation.view.adapter.ScheduleAdapter
import com.calender.presentation.base.BaseFragment
import com.calender.presentation.databinding.FragmentCalenderBinding
import com.calender.presentation.utils.HorizonItemDecorator
import com.calender.presentation.listener.RecyclerViewItemClickListener
import com.calender.presentation.utils.OnSwipeTouchListener
import com.calender.presentation.view.activity.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDate
import kotlin.math.abs

@AndroidEntryPoint
class CalenderFragment : BaseFragment<FragmentCalenderBinding>(R.layout.fragment_calender) {
    private val calenderViewModel : CalenderViewModel by activityViewModels()
    private var lastY = 0
    private var curY = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        val calenderAdapter = CalenderAdapter()
        val monthManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
        binding.customCalender.apply {
            layoutManager = monthManager
            adapter = calenderAdapter
            addItemDecoration(HorizonItemDecorator(10))
            scrollToPosition(Int.MAX_VALUE/2)
            //setHasFixedSize(true)
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

        calenderAdapter.setOnItemClickListener(object : RecyclerViewItemClickListener {
            override fun onItemClickListener(date: LocalDate) {//해당 날짜의 데이터 조회해서 입력
                calenderViewModel.searchScheduleDate(date)//room에서 해당 날짜 데이터 받아와서 viewmodel에 저장
            }
        })

        calenderViewModel.scheduleInfo.observe(viewLifecycleOwner, Observer {
            scheduleAdapter.setItems(it)//다른 날짜 클릭시 해당날짜에 저장된 데이터 불러와서 업데이트
        })

        binding.calenderFragment.setOnTouchListener(object : OnSwipeTouchListener(requireActivity()){//플레그먼트 빈공간만 인식되는 문제(터치권한을 전체로 가져와야??)
            override fun onSwipeTop() {
                binding.calenderLayout.visibility = View.VISIBLE
            }

            override fun onSwipeBottom() {
//                val params = binding.calenderLayout.layoutParams
//                params.height = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,5F,binding.calenderLayout.context.resources.displayMetrics).toInt()
//                binding.calenderLayout.layoutParams
                binding.calenderLayout.visibility = View.GONE
            }
        })

//        binding.calenderFragment.setOnTouchListener { view, motionEvent ->
//            val action = motionEvent.action
//            if(action == MotionEvent.ACTION_DOWN) {
//                lastY = motionEvent.y.toInt()
//                Log.d("scorll","botom22")
//            }else if(action == MotionEvent.ACTION_MOVE ) {//&& (abs(lastY - binding.calenderLayout.layoutParams.height) < 20)
//                curY = motionEvent.y.toInt()
//                val params = binding.calenderLayout.layoutParams
//                params.height = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,(binding.calenderLayout.layoutParams.height + curY - lastY).toFloat(),binding.calenderLayout.context.resources.displayMetrics).toInt()
//                binding.calenderLayout.layoutParams = params
//                lastY = motionEvent.y.toInt()
//                Log.d("scorll","botom")
//            }
//            true
//        }

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        setActionBarTitle("${LocalDate.now().year}.${LocalDate.now().monthValue} v")
        inflater.inflate(R.menu.calender_menu,menu)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.weekMode -> {
                //view?.findNavController()?.navigate(R.id.action_HomeFragment_to_toDoModeFragment2)
            }
        }
        return super.onOptionsItemSelected(item)
    }


}
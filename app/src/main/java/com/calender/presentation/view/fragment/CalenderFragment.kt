package com.calender.presentation.view.fragment

import android.annotation.SuppressLint
import android.graphics.Point
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.calender.domain.model.successOrNull
import com.calender.presentation.R
import com.calender.presentation.data.viewmodels.CalenderViewModel
import com.calender.presentation.view.adapter.CalenderAdapter
import com.calender.presentation.view.adapter.ScheduleAdapter
import com.calender.presentation.base.BaseFragment
import com.calender.presentation.databinding.FragmentCalenderBinding
import com.calender.presentation.utils.HorizonItemDecorator
import com.calender.presentation.listener.RecyclerViewItemClickListener
import com.calender.presentation.listener.OnSwipeTouchListener
import com.google.android.material.bottomsheet.BottomSheetBehavior
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDate

@AndroidEntryPoint
class CalenderFragment : BaseFragment<FragmentCalenderBinding>(R.layout.fragment_calender) {
    private val calenderViewModel : CalenderViewModel by activityViewModels()
    private val calenderAdapter = CalenderAdapter()
    private val scheduleAdapter = ScheduleAdapter()
    private var bottomBehavior :BottomSheetBehavior<ConstraintLayout>? = null

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        bottomBehavior = BottomSheetBehavior.from(binding.bottomScheduleSheet)
        bottomBehavior?.apply {
            state = BottomSheetBehavior.STATE_COLLAPSED
            addBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback(){
                override fun onStateChanged(bottomSheet: View, newState: Int) {
                    when(newState){
                        BottomSheetBehavior.STATE_EXPANDED -> {
                            //완전 펼쳐진 상태
                        }
                        BottomSheetBehavior.STATE_COLLAPSED -> {
                            //절반 상태
                            binding.customCalender.alpha = 1.0F
                            calenderViewModel.setViewHeight((calenderViewModel.parentHeight * 0.45).toInt())
                            calenderAdapter.notifyDataSetChanged()
                        }
                        BottomSheetBehavior.STATE_HIDDEN -> {
                            //숨김상태
                            calenderViewModel.setViewHeight(calenderViewModel.parentHeight)
                            calenderAdapter.notifyDataSetChanged()
                        }
                    }
                }

                override fun onSlide(bottomSheet: View, slideOffset: Float) {
                    //히든 상태가 될때 리사이클러뷰 크기를 조정하는 애니메이션 추가, 펼칠때는 색상도를 낮추는 효과를 추가
                    if (slideOffset < 0.0){
                        binding.calenderLayout.alpha = 1f
                    }else if(slideOffset == 0.0F){
                        binding.calenderLayout.alpha = 1f
                    }else{
                        val slide = 1F - (slideOffset*0.8F + 0.5F)
                        binding.calenderLayout.alpha = if (slide <= 0.25F) 0.25F else slide
                    }
                }
            })
        }
        calenderAdapter.setcParentHeight(calenderViewModel.liveHeight)
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            cAdapter = calenderAdapter
            sAdapter = scheduleAdapter
            customCalender.apply {
                addItemDecoration(HorizonItemDecorator(5))
                scrollToPosition(Int.MAX_VALUE / 2)
                setHasFixedSize(false)
            }
            scheduleList.apply {
                setHasFixedSize(true)
            }
            customCalender.viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener{
                override fun onGlobalLayout() {
                    calenderViewModel.parentHeight = customCalender.height - calenderDays.root.height -5
                    calenderViewModel.setViewHeight((calenderViewModel.parentHeight * 0.45).toInt())
                    bottomBehavior?.peekHeight = (calenderViewModel.parentHeight * 0.55).toInt()
                    customCalender.viewTreeObserver.removeOnGlobalLayoutListener(this)
                }
            })
            customCalender.addOnItemTouchListener(object : OnSwipeTouchListener(requireActivity()){//플레그먼트 빈공간만 인식되는 문제(터치권한을 전체로 가져와야??)
                override fun onSwipeTop() {
                    bottomBehavior?.state = if (bottomBehavior?.state == BottomSheetBehavior.STATE_HIDDEN){
                        BottomSheetBehavior.STATE_COLLAPSED
                    }else
                        BottomSheetBehavior.STATE_EXPANDED
                }

                override fun onSwipeBottom() {
                    if (bottomBehavior?.state == BottomSheetBehavior.STATE_COLLAPSED){
                        bottomBehavior?.state = BottomSheetBehavior.STATE_HIDDEN
                    }
                }

                override fun onSwipeLeft() {
//                    calenderViewModel.setSelectDay(calenderViewModel.liveSelectDay.value?.minusMonths(1)!!)
//                    setActionBarTitle("${calenderViewModel.liveSelectDay.value?.year}.${calenderViewModel.liveSelectDay.value?.monthValue} v")
                    //calenderViewModel.position = calenderViewModel.position -1
                    //customCalender.smoothScrollToPosition(calenderViewModel.position )
                }

                override fun onSwipeRight() {
//                    calenderViewModel.setSelectDay(calenderViewModel.liveSelectDay.value?.plusMonths(1)!!)
//                    setActionBarTitle("${calenderViewModel.liveSelectDay.value?.year}.${calenderViewModel.liveSelectDay.value?.monthValue} v")
                    //calenderViewModel.position = calenderViewModel.position +1
                    //customCalender.smoothScrollToPosition(calenderViewModel.position )
                }
            })

//            calenderFragment.setOnTouchListener { view, ev ->
//                val action = ev?.action
//                when(action){
//                    MotionEvent.ACTION_DOWN -> lastY = ev.y.toInt()
//                    MotionEvent.ACTION_MOVE -> {
//                        var curY = ev.y.toInt()
//                        if (!(curY - OFF_SET <= lastY && lastY <= curY + OFF_SET)){
//                            if (calenderViewModel.mode == 0) {
//                                binding.calenderLayout.visibility = View.VISIBLE
//                                calenderViewModel.mode = 1
//                            } else if (calenderViewModel.mode == 1) {
//                                binding.calenderLayout.visibility = View.GONE
//                                calenderViewModel.mode = 0
//                            }
//                        }
//                    }
//                }
//
//                if(motionEvent.action == MotionEvent.ACTION_MOVE) {
//                    if (calenderViewModel.mode == 0) {
//                        binding.calenderLayout.visibility = View.VISIBLE
//                        calenderViewModel.mode = 1
//                        Log.d("tset", "1")
//                    } else if (calenderViewModel.mode == 1) {
//                        binding.calenderLayout.visibility = View.GONE
//                        calenderViewModel.mode = 0
//                    }
//                }
//                true
//           }
        }
        val sanp = PagerSnapHelper()
        sanp.attachToRecyclerView(binding.customCalender)//달별로 페이지 넘기기


        calenderAdapter.setOnItemClickListener(object : RecyclerViewItemClickListener {
            override fun onItemClickListener(date: LocalDate) {//해당 날짜의 데이터 조회해서 입력
                //bottomsheet에 변경되도록 전달
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
        setActionBarTitle("${calenderViewModel.liveSelectDay.value?.year}.${calenderViewModel.liveSelectDay.value?.monthValue} v")
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
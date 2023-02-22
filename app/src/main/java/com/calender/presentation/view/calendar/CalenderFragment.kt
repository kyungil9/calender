package com.calender.presentation.view.calendar

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.PagerSnapHelper
import com.calender.presentation.R
import com.calender.presentation.base.BaseFragment
import com.calender.presentation.databinding.FragmentCalenderBinding
import com.calender.presentation.utils.HorizonItemDecorator
import com.calender.presentation.listener.RecyclerViewItemClickListener
import com.calender.presentation.listener.OnSwipeTouchListener
import com.calender.presentation.listener.SnapPagerScrollListener
import com.calender.presentation.utils.CalenderUtils
import com.calender.presentation.utils.VerticalItemDecorator
import com.calender.presentation.view.addcalendar.AddCalender
import com.google.android.material.bottomsheet.BottomSheetBehavior
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.ZoneId
import java.time.temporal.ChronoUnit

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
                            //calenderAdapter.notifyDataSetChanged()
                        }
                        BottomSheetBehavior.STATE_HIDDEN -> {
                            //숨김상태
                            calenderViewModel.setViewHeight(calenderViewModel.parentHeight)
                            //calenderAdapter.notifyDataSetChanged()
                        }
                        else -> {}
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
        calenderAdapter.parentHeight = calenderViewModel.liveHeight
        calenderAdapter.selectDay = calenderViewModel.liveSelectDay
        calenderAdapter.calenderData = calenderViewModel.liveMonthSchedule.asLiveData()
        val snap = PagerSnapHelper()
        snap.attachToRecyclerView(binding.customCalender)//달별로 페이지 넘기기
        val snapListener = SnapPagerScrollListener(
            snap,
            SnapPagerScrollListener.ON_SCROLL,
            true,
            object : SnapPagerScrollListener.OnChangeListener{//나중에 달력으로 이동시 작동되는지 확인
                override fun onSnapped(position: Int) {
                    if (position < calenderViewModel.position){
                        calenderViewModel.position = position
                        calenderViewModel.setSelectDay(calenderViewModel.liveSelectDay.value?.minusMonths(1)!!)
                    }else if (position > calenderViewModel.position){
                        calenderViewModel.position = position
                        calenderViewModel.setSelectDay(calenderViewModel.liveSelectDay.value?.plusMonths(1)!!)
                    }
                    setActionBarTitle("${calenderViewModel.liveSelectDay.value?.year}.${calenderViewModel.liveSelectDay.value?.monthValue} v")
                    val monthPeriod = CalenderUtils.getMonthPeriod(calenderViewModel.liveSelectDay.value!!)
                    calenderViewModel.getMonthSchedule(monthPeriod.startDate,monthPeriod.endDate)
                }
            }
        )

        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            cAdapter = calenderAdapter
            sAdapter = scheduleAdapter
            vm = calenderViewModel
            customCalender.apply {
                scrollToPosition(ChronoUnit.MONTHS.between(LocalDate.of(2000,1,1), LocalDate.now().withDayOfMonth(1)).toInt())
                setHasFixedSize(false)
                viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener{
                    override fun onGlobalLayout() {
                        calenderViewModel.parentHeight = customCalender.height
                        calenderViewModel.setViewHeight((calenderViewModel.parentHeight * 0.45).toInt())
                        bottomBehavior?.peekHeight = (calenderViewModel.parentHeight * 0.55).toInt()
                        customCalender.viewTreeObserver.removeOnGlobalLayoutListener(this)
                    }
                })
                addOnItemTouchListener(object : OnSwipeTouchListener(requireActivity()){//플레그먼트 빈공간만 인식되는 문제(터치권한을 전체로 가져와야??)
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
                })
                addOnScrollListener(snapListener)
            }
            scheduleList.apply {
                setHasFixedSize(true)
                addItemDecoration(VerticalItemDecorator(10))
                addItemDecoration(HorizonItemDecorator(20))
            }
            addCalender.setOnClickListener {
                val intent = Intent(activity, AddCalender::class.java)
                intent.putExtra("selectDay",calenderViewModel.liveSelectDay.value)
                startActivity(intent)
            }
        }

        calenderAdapter.setOnItemClickListener(object : RecyclerViewItemClickListener {
            override fun onItemClickListener(date: LocalDate) {//해당 날짜의 데이터 조회해서 입력
                //bottomsheet에 변경되도록 전달
                calenderViewModel.setSelectDay(date)
                //해당 날짜의 데이터 bottom에 보여주기
                var check = false
                for (item in calenderViewModel.liveMonthSchedule.value.list){//해당 날짜의 데이터가 들어있는지 확인
                    if (item.date == calenderViewModel.liveSelectDay.value) {
                        scheduleAdapter.setItems(item.list)
                        check = true
                    }
                }
                if (!check)
                    scheduleAdapter.setItems(arrayListOf())
            }
        })

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                calenderViewModel.liveMonthSchedule.collectLatest {
                    var check = false
                    for (item in calenderViewModel.liveMonthSchedule.value.list) {//해당 날짜의 데이터가 들어있는지 확인
                        if (item.date == calenderViewModel.liveSelectDay.value) {
                            scheduleAdapter.setItems(item.list)
                            check = true
                        }
                    }
                    if (!check)
                        scheduleAdapter.setItems(arrayListOf())
                }
            }
        }

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        setActionBarTitle("${calenderViewModel.liveSelectDay.value?.year}.${calenderViewModel.liveSelectDay.value?.monthValue} v")
        setActionBarListener {
            val dateDialog = DatePickerDialog(requireContext(),
                { _, year, month, dayOfMonth ->
                    calenderViewModel.updatePosition(LocalDate.of(year, month+1, dayOfMonth))
                    setActionBarTitle("${calenderViewModel.liveSelectDay.value?.year}.${calenderViewModel.liveSelectDay.value?.monthValue} v")
                    binding.customCalender.scrollToPosition(calenderViewModel.position)
                    val monthPeriod = CalenderUtils.getMonthPeriod(calenderViewModel.liveSelectDay.value!!)
                    calenderViewModel.getMonthSchedule(monthPeriod.startDate,monthPeriod.endDate)
                },calenderViewModel.liveSelectDay.value?.year!!,
                calenderViewModel.liveSelectDay.value?.monthValue!!-1,
                calenderViewModel.liveSelectDay.value?.dayOfMonth!!
            )
            dateDialog.datePicker.minDate = LocalDate.of(2000,1,1).atTime(0,0).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()
            dateDialog.datePicker.maxDate = LocalDate.of(2049,12,31).atTime(0,0).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()
            dateDialog.show()
        }
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
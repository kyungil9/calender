package com.calender.main.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.calender.main.R
import com.calender.data.model.local.ToDoLocal
import com.calender.data.model.local.ToDoCheckLocal
import com.calender.domain.model.Schedule
import com.calender.main.data.viewmodels.Calender
import com.calender.main.databinding.FragmentHomeBinding
import com.calender.main.ui.adapter.ScheduleAdapter
import com.calender.main.ui.adapter.ToDoAdapter
import com.calender.main.ui.base.BaseFragment
import com.calender.main.ui.base.HorizonItemDecorator
import com.google.android.material.chip.Chip
import java.time.LocalDate
import java.time.LocalTime


class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val calender = Calender()
        var dayListAdapter = calender.createHomeWeek()
        val dailyList = calender.dailyList
        val dayListManager = GridLayoutManager(context,7)
        binding.weekCalender.apply {
            layoutManager = dayListManager
            adapter = dayListAdapter
        }
        dayListAdapter?.submitList(dailyList)

        val keys = ArrayList<String>()//추후에 viewmodel에 연결
        keys.add("test1")
        keys.add("test2")
        for(key in keys){
            binding.recordChipGroup.addView(createChip(key))
        }

        //todo부분
        val toDoAdapter = ToDoAdapter()
        val toDoManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
        binding.homeTodoList.apply {
            layoutManager = toDoManager
            adapter = toDoAdapter
            addItemDecoration(HorizonItemDecorator(20))
        }

        val todoList = ArrayList<ToDoLocal>()//추후 번경
        var checkList = ArrayList<ToDoCheckLocal>()
        checkList.add(ToDoCheckLocal(LocalDate.now(),"11",false))
        checkList.add(ToDoCheckLocal(LocalDate.now(),"12",true))
        todoList.add(ToDoLocal(LocalDate.now(),checkList))
        toDoAdapter.submitList(todoList)

        //일정부분
        val dumy = ArrayList<Schedule>()
        dumy.add(Schedule(LocalDate.now(), LocalTime.now(),"test1"))
        dumy.add(Schedule(LocalDate.now(), LocalTime.now(),"test2"))
        val scheduleAdapter = ScheduleAdapter()
        val scheduleManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
        binding.homeScheduleList.apply {
            layoutManager = scheduleManager
            adapter = scheduleAdapter
            setHasFixedSize(true)
        }
        scheduleAdapter.setItems(dumy)
    }

    private fun createChip(key:String) : Chip {
        val chip = Chip(requireActivity()).apply {
            text = key
            isCheckable = true
            setOnCheckedChangeListener { value, checked ->
                if (checked) {
                    //추후 서버에 기록 저장부분 처리추가
                } else {

                }
            }
        }
        return chip
    }
}
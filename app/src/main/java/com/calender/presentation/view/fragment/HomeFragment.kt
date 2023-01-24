package com.calender.presentation.view.fragment

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.calender.domain.model.ToDo
import com.calender.domain.model.Schedule
import com.calender.domain.model.ToDoCheck
import com.calender.presentation.R
import com.calender.presentation.utils.Calender
import com.calender.presentation.view.adapter.ScheduleAdapter
import com.calender.presentation.view.adapter.ToDoAdapter
import com.calender.presentation.base.BaseFragment
import com.calender.presentation.databinding.FragmentHomeBinding
import com.calender.presentation.utils.HorizonItemDecorator
import com.calender.presentation.view.activity.MainActivity
import com.google.android.material.chip.Chip
import java.time.LocalDate
import java.time.LocalTime


class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)

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
            binding.recordChipGroup.addView(createChip(key))//bindingAdapter로 이동
        }

        //todo부분
        val toDoAdapter = ToDoAdapter()
        val toDoManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
        binding.homeTodoList.apply {
            layoutManager = toDoManager
            adapter = toDoAdapter
            addItemDecoration(HorizonItemDecorator(20))
        }


        //toDoAdapter.submitList()

        //일정부분

        val scheduleAdapter = ScheduleAdapter()
        val scheduleManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
        binding.homeScheduleList.apply {
            layoutManager = scheduleManager
            adapter = scheduleAdapter
            setHasFixedSize(true)
        }
        //scheduleAdapter.setItems()
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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        val date = LocalDate.now()
        setActionBarTitle("${date.year}.${date.monthValue}.${date.dayOfMonth}(${Calender.transDayToKorean(date.dayOfWeek.value)})")
        inflater.inflate(R.menu.regular_menu,menu)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.setting -> {
                //view?.findNavController()?.navigate(R.id.action_HomeFragment_to_toDoModeFragment2)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
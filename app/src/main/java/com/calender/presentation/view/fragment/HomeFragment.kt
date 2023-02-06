package com.calender.presentation.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.activityViewModels
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
import com.calender.presentation.data.viewmodels.RecordViewModel
import com.calender.presentation.databinding.FragmentHomeBinding
import com.calender.presentation.utils.HorizonItemDecorator
import com.calender.presentation.view.activity.MainActivity
import com.google.android.material.chip.Chip
import java.time.LocalDate
import java.time.LocalTime


class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {
    private val recordViewModel : RecordViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            vm = recordViewModel

            recordChipGroup.setOnCheckedStateChangeListener { group, _ ->
                val id = group.checkedChipId
                if (id != View.NO_ID){
                    when(group.findViewById<Chip>(id).text){
                        //when사용 안하고 그냥 record추가되서 변경되는걸로 내용추가
                    }
                }else{
                    //강제로 휴식에 클릭되도록 설정
                }
            }
        }



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
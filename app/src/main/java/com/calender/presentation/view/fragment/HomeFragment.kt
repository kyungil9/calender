package com.calender.presentation.view.fragment

import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.core.view.get
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.calender.domain.model.*
import com.calender.presentation.R
import com.calender.presentation.utils.Calender
import com.calender.presentation.view.adapter.ScheduleAdapter
import com.calender.presentation.view.adapter.ToDoAdapter
import com.calender.presentation.base.BaseFragment
import com.calender.presentation.data.viewmodels.RecordViewModel
import com.calender.presentation.databinding.FragmentHomeBinding
import com.calender.presentation.listener.CustomDialogListener
import com.calender.presentation.listener.RecyclerViewToDoClickListener
import com.calender.presentation.utils.CustomDialog
import com.calender.presentation.utils.HorizonItemDecorator
import com.calender.presentation.utils.VerticalItemDecorator
import com.calender.presentation.view.activity.AddToDo
import com.calender.presentation.view.activity.MainActivity
import com.calender.presentation.view.adapter.ToDoCheckAdapter
import com.google.android.material.chip.Chip
import java.time.LocalDate
import java.time.LocalTime


class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home),CustomDialogListener {
    private val recordViewModel : RecordViewModel by activityViewModels()
    private val todoCheckAdapter = ToDoCheckAdapter()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            homeTodayTodo.apply {
                adapter = todoCheckAdapter
                homeVm = recordViewModel
                itemTodoList.apply {
                    addItemDecoration(HorizonItemDecorator(10))
                    addItemDecoration(VerticalItemDecorator(10))
                }
            }
            vm = recordViewModel
            recordChipGroup.setOnCheckedStateChangeListener { group, _ ->
                val id = group.checkedChipId
                if (id != View.NO_ID && group.findViewById<Chip>(id).text.toString() != recordViewModel.selectRecord.value.successOrNull()?.tag){
                    recordViewModel.updateRecord()//기존 내용은 db저장
                    recordViewModel.insertRecord(group.findViewById<Chip>(id).text.toString())//새로운 기록 작성
                }
            }

            fabHomeMain.setOnClickListener {
                toggleFab()
            }
            fabHomeTag.setOnClickListener {
                val dialog = CustomDialog(requireContext(),this@HomeFragment)
                dialog.show()
                toggleFab()
            }
            fabHomeToDo.setOnClickListener {
                val intent = Intent(context, AddToDo::class.java)
                startActivity(intent)
                toggleFab()
            }
            fabHomeCalender.setOnClickListener {
                recordViewModel.updateLiveToday()
                toggleFab()
            }

        }
        todoCheckAdapter.setOnItemClickListener(object : RecyclerViewToDoClickListener {
            override fun onItemClickListener(item: ToDoCheck,mode : ToDoCheckMode) {
                when(mode){
                    ToDoCheckMode.STATE -> {
                        recordViewModel.updateToDoState(item.state,item.id)
                    }
                    ToDoCheckMode.STATEPERCENT -> {
                        recordViewModel.updateToDoStatePercent(item.statePercent,item.id)
                    }
                }
            }
        })
        recordViewModel.timer.schedule(recordViewModel.timerTask,0,1000)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        setActionBarTitle("${recordViewModel.liveTodayDate.value?.year}.${recordViewModel.liveTodayDate.value?.monthValue}.${recordViewModel.liveTodayDate.value?.dayOfMonth}(${Calender.transDayToKorean(recordViewModel.liveTodayDate.value?.dayOfWeek!!.value)})")
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

    @SuppressLint("ObjectAnimatorBinding")
    fun toggleFab(){
        if (recordViewModel.fabMain_status){
            binding.fabHomeMain.setImageResource(R.drawable.ic_baseline_add_24)
            val fTodoIn = ObjectAnimator.ofFloat(binding.fabHomeToDo,"translationX",0f).setDuration(500)
            val fCalenderIn = ObjectAnimator.ofFloat(binding.fabHomeCalender,"translationY",0f).setDuration(500)
            val txIn = PropertyValuesHolder.ofFloat("translationX",0f)
            val tyIn = PropertyValuesHolder.ofFloat("translationY",0f)
            val fTagIn = ObjectAnimator.ofPropertyValuesHolder(binding.fabHomeTag,txIn,tyIn).setDuration(500)
            fTodoIn.start()
            fCalenderIn.start()
            fTagIn.start()
        }else{
            binding.fabHomeMain.setImageResource(R.drawable.ic_baseline_cancel_white)
            val fTodoOut = ObjectAnimator.ofFloat(binding.fabHomeToDo,"translationX",-200f).setDuration(500)
            val fCalenderOut = ObjectAnimator.ofFloat(binding.fabHomeCalender,"translationY",-200f).setDuration(500)
            val txOut = PropertyValuesHolder.ofFloat("translationX",-150f)
            val tyOut = PropertyValuesHolder.ofFloat("translationY",-150f)
            val fTagOut = ObjectAnimator.ofPropertyValuesHolder(binding.fabHomeTag,txOut,tyOut).setDuration(500)
            fTodoOut.start()
            fCalenderOut.start()
            fTagOut.start()
        }
        recordViewModel.fabMain_status = !recordViewModel.fabMain_status
    }

    override fun onButtonClicked(text: String) {
        recordViewModel.insertHomeTag(text)
    }
}
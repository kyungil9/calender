package com.calender.presentation.view.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import com.calender.domain.model.ToDoCheck
import com.calender.domain.model.ToDoCheckMode
import com.calender.presentation.R
import com.calender.presentation.base.BaseFragment
import com.calender.presentation.base.BaseToDoFragment
import com.calender.presentation.data.viewmodels.ToDoModeViewModel
import com.calender.presentation.data.viewmodels.ToDoViewModel
import com.calender.presentation.databinding.FragmentToDoModeBinding
import com.calender.presentation.listener.RecyclerViewToDoClickListener
import com.calender.presentation.utils.HorizonItemDecorator
import com.calender.presentation.utils.VerticalItemDecorator
import com.calender.presentation.view.activity.AddToDo
import com.calender.presentation.view.activity.MainActivity
import com.calender.presentation.view.adapter.ToDoAdapter


class ToDoModeFragment : BaseToDoFragment<FragmentToDoModeBinding>(R.layout.fragment_to_do_mode) {
    private val todoModeViewModel : ToDoModeViewModel by activityViewModels()
    private val todoAdapter : ToDoAdapter by lazy {
        ToDoAdapter()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        actionId = R.id.action_homeFragment_to_toDoFragment
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            vm = todoModeViewModel
            adapter = todoAdapter
            todoMode.addToDo.setOnClickListener {
                addToDoCheck()
            }
            todoMode.customTodo.apply {
                setHasFixedSize(true)
                addItemDecoration(VerticalItemDecorator(10))
                addItemDecoration(HorizonItemDecorator(10))
            }
        }
        todoAdapter.setOnItemClickListener(object : RecyclerViewToDoClickListener {
            override fun onItemClickListener(item: ToDoCheck, mode : ToDoCheckMode) {
                when(mode){
                    ToDoCheckMode.STATE -> {
                        todoModeViewModel.updateToDoState(item.state,item.id)
                    }
                    ToDoCheckMode.STATEPERCENT -> {
                        todoModeViewModel.updateToDoStatePercent(item.statePercent,item.id)
                    }
                }
            }
        })


    }
}
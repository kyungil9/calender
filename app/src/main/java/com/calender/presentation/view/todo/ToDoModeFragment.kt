package com.calender.presentation.view.todo

import android.os.Bundle
import android.view.*
import androidx.fragment.app.activityViewModels
import com.calender.domain.model.ToDoCheck
import com.calender.domain.model.ToDoCheckMode
import com.calender.presentation.R
import com.calender.presentation.base.BaseToDoFragment
import com.calender.presentation.databinding.FragmentToDoModeBinding
import com.calender.presentation.listener.RecyclerViewToDoClickListener
import com.calender.presentation.utils.HorizonItemDecorator
import com.calender.presentation.utils.VerticalItemDecorator


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
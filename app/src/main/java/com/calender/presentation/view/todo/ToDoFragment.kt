package com.calender.presentation.view.todo

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.calender.domain.model.ToDoCheck
import com.calender.domain.model.ToDoCheckMode
import com.calender.presentation.R
import com.calender.presentation.base.BaseToDoFragment
import com.calender.presentation.databinding.FragmentToDoBinding
import com.calender.presentation.listener.RecyclerViewToDoClickListener
import com.calender.presentation.utils.HorizonItemDecorator
import com.calender.presentation.utils.VerticalItemDecorator


class ToDoFragment : BaseToDoFragment<FragmentToDoBinding>(R.layout.fragment_to_do) {
    private val todoViewModel : ToDoViewModel by activityViewModels()
    private val todoAdapter : ToDoAdapter by lazy {
        ToDoAdapter()
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        actionId = R.id.action_HomeFragment_to_toDoModeFragment2
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            vm = todoViewModel
            adapter = todoAdapter
            addToDo.setOnClickListener {
                addToDoCheck()
            }
            customTodo.apply {
                setHasFixedSize(true)
                addItemDecoration(VerticalItemDecorator(10))
                addItemDecoration(HorizonItemDecorator(10))
            }
        }
        todoAdapter.setOnItemClickListener(object : RecyclerViewToDoClickListener {
            override fun onItemClickListener(item: ToDoCheck,mode : ToDoCheckMode) {
                when(mode){
                    ToDoCheckMode.STATE -> {
                        todoViewModel.updateToDoState(item.state,item.id)
                    }
                    ToDoCheckMode.STATEPERCENT -> {
                        todoViewModel.updateToDoStatePercent(item.statePercent,item.id)
                    }
                }
            }
        })

    }


}
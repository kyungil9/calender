package com.calender.presentation.view.fragment

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.calender.domain.model.ToDo
import com.calender.domain.model.ToDoCheck
import com.calender.presentation.R
import com.calender.presentation.view.adapter.ToDoAdapter
import com.calender.presentation.base.BaseFragment
import com.calender.presentation.databinding.FragmentToDoBinding
import com.calender.presentation.utils.HorizonItemDecorator
import com.calender.presentation.utils.VerticalItemDecorator
import java.time.LocalDate


class ToDoFragment : BaseFragment<FragmentToDoBinding>(R.layout.fragment_to_do) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val toDoAdapter = ToDoAdapter()
        val toDoManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
        binding.customTodo.apply {
            adapter = toDoAdapter
            layoutManager = toDoManager
            setHasFixedSize(true)
            addItemDecoration(VerticalItemDecorator(10))
            addItemDecoration(HorizonItemDecorator(10))
        }
        //dumydata
        val todoList = ArrayList<ToDo>()//추후 번경
        var checkList = ArrayList<ToDoCheck>()
        checkList.add(ToDoCheck(LocalDate.now(),"11",false))
        checkList.add(ToDoCheck(LocalDate.now(),"12",true))
        todoList.add(ToDo(LocalDate.now(),checkList))
        checkList = ArrayList<ToDoCheck>()
        checkList.add(ToDoCheck(LocalDate.now(),"12",true))
        todoList.add(ToDo(LocalDate.now(),checkList))
        toDoAdapter.submitList(todoList)

    }


}
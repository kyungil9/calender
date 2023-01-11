package com.calender.main.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.calender.main.R
import com.calender.data.model.local.ToDoLocal
import com.calender.data.model.local.ToDoCheckLocal
import com.calender.main.databinding.FragmentToDoBinding
import com.calender.main.ui.adapter.ToDoAdapter
import com.calender.main.ui.base.BaseFragment
import com.calender.main.ui.base.HorizonItemDecorator
import com.calender.main.ui.base.VerticalItemDecorator
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
        val todoList = ArrayList<ToDoLocal>()//추후 번경
        var checkList = ArrayList<ToDoCheckLocal>()
        checkList.add(ToDoCheckLocal(LocalDate.now(),"11",false))
        checkList.add(ToDoCheckLocal(LocalDate.now(),"12",true))
        todoList.add(ToDoLocal(LocalDate.now(),checkList))
        checkList = ArrayList<ToDoCheckLocal>()
        checkList.add(ToDoCheckLocal(LocalDate.now(),"12",true))
        todoList.add(ToDoLocal(LocalDate.now(),checkList))
        toDoAdapter.submitList(todoList)

    }


}
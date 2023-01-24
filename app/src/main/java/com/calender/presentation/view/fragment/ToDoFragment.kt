package com.calender.presentation.view.fragment

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.calender.presentation.R
import com.calender.presentation.base.BaseFragment
import com.calender.presentation.data.viewmodels.ToDoViewModel
import com.calender.presentation.databinding.FragmentToDoBinding
import com.calender.presentation.utils.HorizonItemDecorator
import com.calender.presentation.utils.VerticalItemDecorator
import com.calender.presentation.view.activity.MainActivity
import com.calender.presentation.view.adapter.ToDoAdapter


class ToDoFragment : BaseFragment<FragmentToDoBinding>(R.layout.fragment_to_do) {
    private val todoViewModel : ToDoViewModel by activityViewModels()
    private val todoAdapter = ToDoAdapter()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        binding.apply {
            vm = todoViewModel
            adapter = todoAdapter
            customTodo.apply {
                setHasFixedSize(true)
                addItemDecoration(VerticalItemDecorator(10))
                addItemDecoration(HorizonItemDecorator(10))
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        setActionBarTitle("할 일")
        inflater.inflate(R.menu.todo_menu,menu)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.changeToDoMode -> {
                view?.findNavController()?.navigate(R.id.action_HomeFragment_to_toDoModeFragment2)
            }
        }
        return super.onOptionsItemSelected(item)
    }

}
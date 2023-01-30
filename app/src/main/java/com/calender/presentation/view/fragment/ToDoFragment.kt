package com.calender.presentation.view.fragment

import android.app.ActivityOptions
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.core.content.ContextCompat.startActivity
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.calender.domain.model.Result
import com.calender.presentation.R
import com.calender.presentation.base.BaseFragment
import com.calender.presentation.data.viewmodels.ToDoViewModel
import com.calender.presentation.databinding.FragmentToDoBinding
import com.calender.presentation.utils.HorizonItemDecorator
import com.calender.presentation.utils.VerticalItemDecorator
import com.calender.presentation.utils.bindShow
import com.calender.presentation.utils.bindToDoItems
import com.calender.presentation.view.activity.AddToDo
import com.calender.presentation.view.activity.MainActivity
import com.calender.presentation.view.adapter.ToDoAdapter
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class ToDoFragment : BaseFragment<FragmentToDoBinding>(R.layout.fragment_to_do) {
    private val todoViewModel : ToDoViewModel by activityViewModels()
    private val todoAdapter = ToDoAdapter()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
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
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
//                todoViewModel.programResult.collectLatest { result->
//                    binding.progressBar.bindShow(result)
//                }
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
    fun addToDoCheck(){
        val intent = Intent(context,AddToDo::class.java)
        startActivity(intent)
    }
}
package com.calender.presentation.view.fragment

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.calender.presentation.R
import com.calender.presentation.base.BaseFragment
import com.calender.presentation.databinding.FragmentToDoModeBinding
import com.calender.presentation.view.activity.MainActivity


class ToDoModeFragment : BaseFragment<FragmentToDoModeBinding>(R.layout.fragment_to_do_mode) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)


    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        setActionBarTitle("할 일")
        inflater.inflate(R.menu.todo_menu,menu)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.changeToDoMode -> {
                //view?.findNavController()?.navigate(R.id.action_HomeFragment_to_toDoModeFragment2)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
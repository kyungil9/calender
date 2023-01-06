package com.calender.main.ui.fragment

import android.os.Bundle
import android.view.View
import com.calender.main.R
import com.calender.main.databinding.FragmentToDoBinding
import com.calender.main.ui.adapter.ToDoAdapter
import com.calender.main.ui.base.BaseFragment


class ToDoFragment : BaseFragment<FragmentToDoBinding>(R.layout.fragment_to_do) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.customTodo.apply {
            adapter = ToDoAdapter()
            setHasFixedSize(true)
        }


    }


}
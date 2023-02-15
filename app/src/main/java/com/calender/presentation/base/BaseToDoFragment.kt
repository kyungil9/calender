package com.calender.presentation.base

import android.content.Intent
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
import androidx.navigation.findNavController
import com.calender.presentation.R
import com.calender.presentation.view.activity.AddToDo

abstract class BaseToDoFragment<T : ViewDataBinding>(@LayoutRes private val layoutRes: Int) : BaseFragment<T>(layoutRes) {
    var actionId = 0

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        setActionBarTitle("할 일")
        setActionBarListener(null)
        inflater.inflate(R.menu.todo_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.changeToDoMode -> {
                view?.findNavController()?.navigate(actionId)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun addToDoCheck(){
        val intent = Intent(context, AddToDo::class.java)
        startActivity(intent)
    }
}
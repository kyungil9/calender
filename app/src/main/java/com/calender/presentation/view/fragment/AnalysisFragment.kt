package com.calender.presentation.view.fragment


import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.navigation.findNavController
import com.calender.presentation.R
import com.calender.presentation.base.BaseFragment
import com.calender.presentation.databinding.FragmentAnalysisBinding

class AnalysisFragment : BaseFragment<FragmentAnalysisBinding>(R.layout.fragment_analysis) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        setActionBarTitle("분 석")
        inflater.inflate(R.menu.regular_menu,menu)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.setting -> {
                //view?.findNavController()?.navigate(R.id.action_HomeFragment_to_toDoModeFragment2)
            }
        }
        return super.onOptionsItemSelected(item)
    }

}
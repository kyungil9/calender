package com.calender.presentation.view.memo

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.activityViewModels
import com.calender.presentation.R
import com.calender.presentation.base.BaseFragment
import com.calender.presentation.databinding.FragmentMemoBinding


class MemoFragment : BaseFragment<FragmentMemoBinding>(R.layout.fragment_memo) {
    private val memoViewModel : MemoViewModel by activityViewModels()
    private val memoAdapter = MemoAdapter()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)

        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            adapter = memoAdapter
        }
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        setActionBarTitle("메 모")
        setActionBarListener(null)
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
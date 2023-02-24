package com.calender.presentation.view.memo

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.activityViewModels
import com.calender.domain.model.Memo
import com.calender.presentation.R
import com.calender.presentation.base.BaseFragment
import com.calender.presentation.databinding.FragmentMemoBinding
import com.calender.presentation.listener.RecyclerViewMemoClickListener
import com.calender.presentation.utils.VerticalItemDecorator
import com.calender.presentation.view.addMemo.AddMemo


class MemoFragment : BaseFragment<FragmentMemoBinding>(R.layout.fragment_memo) {
    private val memoViewModel : MemoViewModel by activityViewModels()
    private val memoAdapter = MemoAdapter()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)

        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            adapter = memoAdapter
            vm = memoViewModel
            addMemo.setOnClickListener {
                val intent = Intent(activity,AddMemo::class.java)
                startActivity(intent)
            }
        }
        memoAdapter.setOnMemoClickListener(object :RecyclerViewMemoClickListener{
            override fun onMemoClickListener(memo: Memo) {
                val intent = Intent(activity,AddMemo::class.java)
                intent.putExtra("selectMemo",memo)
                startActivity(intent)
            }
        })

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
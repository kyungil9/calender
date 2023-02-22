package com.calender.presentation.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.calender.presentation.view.main.MainActivity

abstract class BaseFragment<T: ViewDataBinding>(@LayoutRes private val layoutRes: Int) : Fragment() {
    private var _binding : T ?= null
    val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding= DataBindingUtil.inflate(inflater,layoutRes,container,false)
        return binding.root
    }

    override fun onDestroyView() {
        _binding=null
        super.onDestroyView()
    }

    override fun onResume() {
        super.onResume()
        activity?.invalidateOptionsMenu()
    }

    fun setActionBarTitle(title:String){
        if (activity != null) {
            (activity as MainActivity).setActionBarTitle(title)
        }
    }

    fun setActionBarListener(listener : View.OnClickListener?){
        if (activity != null)
            (activity as MainActivity).setActionBarClickListener(listener)
    }
}
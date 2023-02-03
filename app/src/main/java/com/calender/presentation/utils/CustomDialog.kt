package com.calender.presentation.utils

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.text.Layout
import android.view.LayoutInflater
import androidx.databinding.DataBindingUtil
import com.calender.presentation.R
import com.calender.presentation.databinding.CustomDialogBinding
import com.calender.presentation.listener.CustomDialogListener

class CustomDialog(context : Context, private val listener : CustomDialogListener) : Dialog(context) {
    private lateinit var binding : CustomDialogBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.custom_dialog,
            null,false
        )
        setContentView(binding.root)

        binding.apply {
            successButton.setOnClickListener {
                listener.onButtonClicked(binding.tagNameEdit.text.toString())
                dismiss()
            }
        }
    }
}
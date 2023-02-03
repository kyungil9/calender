package com.calender.presentation.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.calender.domain.model.successOrNull
import com.calender.presentation.R
import com.calender.presentation.base.BaseActivity
import com.calender.presentation.data.viewmodels.TagViewModel
import com.calender.presentation.databinding.ActivityAddTagBinding
import com.calender.presentation.listener.CustomDialogListener
import com.calender.presentation.listener.RecyclerViewTagClickListener
import com.calender.presentation.utils.CustomDialog
import com.calender.presentation.utils.HorizonItemDecorator
import com.calender.presentation.utils.VerticalItemDecorator
import com.calender.presentation.view.adapter.TagAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AddTag : BaseActivity<ActivityAddTagBinding>(R.layout.activity_add_tag,TransitionMode.HORIZON),CustomDialogListener {
    private val viewModel : TagViewModel by viewModels()
    private val tagAdapter : TagAdapter by lazy {
        TagAdapter()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(binding.todoToolbar.toolbar)
        supportActionBar?.apply {
            setDisplayShowTitleEnabled(false)
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_24)
        }
        binding.apply {
            lifecycleOwner = this@AddTag
            vm = viewModel
            adapter = tagAdapter
            todoToolbar.toolbarTitle.text = "태그 등록"

            listTag.apply {
                setHasFixedSize(true)
                addItemDecoration(VerticalItemDecorator(5))
                addItemDecoration(HorizonItemDecorator(10))
            }
        }
        tagAdapter.setOnItemClickListener(object : RecyclerViewTagClickListener {
            override fun onItemClickListener(item: String) {
                viewModel.selectTag = item
            }
        })

//        lifecycleScope.launch {
//            repeatOnLifecycle(Lifecycle.State.STARTED){
//                viewModel.tagResult.collectLatest {
//                    tagAdapter.submitList(it.successOrNull())
//                }
//            }
//        }

    }



    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.tag_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            android.R.id.home -> {
                finish()
                true
            }
            R.id.add_tag -> {
                val dialog = CustomDialog(this,this)
                dialog.show()
                true
            }
            R.id.register_tag -> {
                intent.putExtra("tag",viewModel.selectTag)
                setResult(RESULT_OK,intent)
                finish()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onButtonClicked(tag : String) {
        viewModel.insertTag(tag)
    }
}
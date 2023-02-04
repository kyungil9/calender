package com.calender.presentation.view.activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
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
        val defaultTag = intent.getStringExtra("defaultTag")
        val defaultIndex = intent.getIntExtra("defaultIndex",0)
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
            override fun onItemClickListener(item: String,index : Int) {
                viewModel.selectTag = item
                viewModel.selectIndex = index
            }
        })
        viewModel.selectTag = defaultTag!!
        viewModel.selectIndex = defaultIndex
        tagAdapter.setDefaultTag(viewModel.selectIndex)

        itemTouch()
    }

    private fun itemTouch(){
        val itemTouchCallback = object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,ItemTouchHelper.LEFT
        ){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                viewModel.deleteTag(viewModel.tagResult.value.successOrNull()?.get(viewHolder.adapterPosition)!!)
            }
        }
        ItemTouchHelper(itemTouchCallback).attachToRecyclerView(binding.listTag)
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
                intent.putExtra("index",viewModel.selectIndex)
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
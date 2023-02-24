package com.calender.presentation.view.addMemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import com.calender.domain.model.Memo
import com.calender.presentation.R
import com.calender.presentation.base.BaseActivity
import com.calender.presentation.databinding.ActivityAddMemoBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddMemo : BaseActivity<ActivityAddMemoBinding>(R.layout.activity_add_memo,TransitionMode.VERTICAL) {
    private val viewModel : MemoAddViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(binding.memoToolbar.toolbar)
        supportActionBar?.apply {
            setDisplayShowTitleEnabled(false)
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_24)
        }
        val select = intent.getSerializableExtra("selectMemo") as Memo?
        select?.let {
            viewModel.setUpdateMemo(it)
            viewModel.newMemo = false
        }

        binding.apply {
            lifecycleOwner = this@AddMemo
            vm = viewModel
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.memo_menu,menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            android.R.id.home -> {
                viewModel.check = false
                finish()
                true
            }
            R.id.register -> {
                //입력된 데이터를 저장하는 코드와 함께 종료
                viewModel.check = true
                finish()
                true
            }
            R.id.delectMemo ->{
                viewModel.check = false
                if (!viewModel.newMemo)
                    viewModel.delectMemo()
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onStop() {
        super.onStop()
        if (viewModel.check) {
            if (viewModel.newMemo) {
                viewModel.insertMemo()
            } else
                viewModel.updateMemo()
        }
    }
}
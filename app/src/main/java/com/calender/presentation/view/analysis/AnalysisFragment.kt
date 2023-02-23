package com.calender.presentation.view.analysis


import android.graphics.Color
import android.os.Bundle
import android.view.*
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.findNavController
import com.calender.presentation.R
import com.calender.presentation.base.BaseFragment
import com.calender.presentation.databinding.FragmentAnalysisBinding
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.utils.ColorTemplate
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class AnalysisFragment : BaseFragment<FragmentAnalysisBinding>(R.layout.fragment_analysis) {
    private val analysisViewModel : AnalysisViewModel by activityViewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        binding.apply {
            lifecycleOwner = viewLifecycleOwner

        }
        pieInit()
    }

    private fun pieInit(){
        binding.analysisPieChart.apply {
            setUsePercentValues(true)
            description.isEnabled = false
            setExtraOffsets(5f,10f,5f,5f)
            dragDecelerationFrictionCoef= 0.95f
            isDrawHoleEnabled = false
            setHoleColor(Color.WHITE)
            transparentCircleRadius = 61f
            description = analysisViewModel.description
            animateY(1000, Easing.EaseInOutCubic)
        }
        lifecycleScope.launch(Dispatchers.IO){
            repeatOnLifecycle(Lifecycle.State.STARTED){
                analysisViewModel.liveDayRecord.collectLatest {
                    val yValue = ArrayList<PieEntry>()
                    if (it.isNotEmpty()) {
                        for (record in it) {
                            yValue.add(PieEntry(record.progressTime.toFloat(), record.tag))
                        }
                        val pieDataSet = PieDataSet(yValue,"오늘 한일")
                        pieDataSet.apply {
                            sliceSpace = 3f
                            selectionShift = 5f
                            colors = listOf(Color.BLUE,Color.CYAN,Color.GREEN,Color.LTGRAY,Color.MAGENTA,Color.RED,Color.TRANSPARENT,Color.YELLOW)
                        }
                        val data = PieData(pieDataSet)
                        data.setValueTextSize(10f)
                        data.setValueTextColor(Color.BLACK)
                        binding.analysisPieChart.data = data
                    }
                }
            }
        }

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        setActionBarTitle("분 석")
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
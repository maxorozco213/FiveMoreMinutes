package com.example.androidfmm.alarm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidfmm.R
import com.example.androidfmm.databinding.FragmentAlarmListBinding
import kotlinx.android.synthetic.main.fragment_alarm_list.*

class AlarmListFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentAlarmListBinding>(
            inflater, R.layout.fragment_alarm_list, container, false)
        binding.alarmList = this
        
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        //Test method call
//        val testList = generateTest(100)

        val alarmItemsList = listOf(
            AlarmItem(0, dayOfWeek = "Monday", alarmTime = "12:00PM", alarmDate = "8/31/20"),
            AlarmItem(0, dayOfWeek = "Monday", alarmTime = "12:00PM", alarmDate = "8/31/20"),
            AlarmItem(0, dayOfWeek = "Monday", alarmTime = "12:00PM", alarmDate = "8/31/20"),
            AlarmItem(0, dayOfWeek = "Monday", alarmTime = "12:00PM", alarmDate = "8/31/20"),
            AlarmItem(0, dayOfWeek = "Monday", alarmTime = "12:00PM", alarmDate = "8/31/20"),
            AlarmItem(0, dayOfWeek = "Monday", alarmTime = "12:00PM", alarmDate = "8/31/20"),
            AlarmItem(0, dayOfWeek = "Monday", alarmTime = "12:00PM", alarmDate = "8/31/20"),
            AlarmItem(0, dayOfWeek = "Monday", alarmTime = "12:00PM", alarmDate = "8/31/20"),
            AlarmItem(0, dayOfWeek = "Monday", alarmTime = "12:00PM", alarmDate = "8/31/20")
        )

        alarm_list_fragment.adapter = AlarmListAdapter(alarmItemsList)
        alarm_list_fragment.layoutManager = LinearLayoutManager(requireContext())
        alarm_list_fragment.setHasFixedSize(true)
    }

    fun goToAlarmCreate() {

    }

    // Test method
//    private fun generateTest(size: Int): List<AlarmItem> {
//        val list = ArrayList<AlarmItem>()
//
//        for (i in 0 until size) {
//            val item = AlarmItem(i.toString())
//            list += item
//        }
//
//        return list
//    }
}
package com.example.androidfmm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import com.example.androidfmm.databinding.FragmentAlarmListBinding

class AlarmList : Fragment() {

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
}
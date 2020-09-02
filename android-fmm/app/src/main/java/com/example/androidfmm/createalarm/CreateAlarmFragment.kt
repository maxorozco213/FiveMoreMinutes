package com.example.androidfmm.createalarm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.androidfmm.R
import com.example.androidfmm.databinding.FragmentCreateAlarmBinding

class CreateAlarmFragment: Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentCreateAlarmBinding>(
            inflater, R.layout.fragment_create_alarm, container, false)
        binding.createAlarm = this

        return binding.root
    }
}
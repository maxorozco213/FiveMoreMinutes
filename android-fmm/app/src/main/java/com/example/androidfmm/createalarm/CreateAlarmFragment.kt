package com.example.androidfmm.createalarm

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.androidfmm.R
import com.example.androidfmm.alarm.AlarmItem
import com.example.androidfmm.data.AlarmViewModel
import com.example.androidfmm.databinding.FragmentCreateAlarmBinding
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.fragment_create_alarm.*

class CreateAlarmFragment: Fragment() {
    private lateinit var mAlarmViewModel: AlarmViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentCreateAlarmBinding>(
            inflater, R.layout.fragment_create_alarm, container, false)
        binding.createAlarm = this

        mAlarmViewModel = ViewModelProvider(this).get(AlarmViewModel::class.java)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.actionbar_save, menu)
        super.onCreateOptionsMenu(menu, menuInflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.save_button -> {
            insertNewAlarmToDatabase()
            Log.i("MENU", "SAVE BUTTON")
            true
        }
        R.id.nav_host_fragment -> {
            NavigationUI.onNavDestinationSelected(
                item,
            requireView().findNavController())
                    || super.onOptionsItemSelected(item)
            true
        }
        else -> {
            Log.i("MENU", "WAT_CREATE")
            super.onOptionsItemSelected(item)
        }
    }

    private fun insertNewAlarmToDatabase() {
        val alarmName = alarmName.text.toString()

        if (inputCheck(alarmName)) {
            val alarmItem = AlarmItem(0, alarmName)
            mAlarmViewModel.addAlarm(alarmItem)
            Toast.makeText(requireContext(), "New Alarm Group Created", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(requireContext(), "There was an Error", Toast.LENGTH_LONG).show()
        }
    }

    private fun inputCheck(alarmName: String): Boolean {
        return !(TextUtils.isEmpty(alarmName))
    }
}
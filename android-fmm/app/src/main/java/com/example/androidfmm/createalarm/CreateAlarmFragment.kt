package com.example.androidfmm.createalarm

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.androidfmm.R
import com.example.androidfmm.databinding.FragmentCreateAlarmBinding
import com.google.android.material.navigation.NavigationView

class CreateAlarmFragment: Fragment() {
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

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.actionbar_save, menu)
        super.onCreateOptionsMenu(menu, menuInflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.save_button -> {
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
}
package com.example.androidfmm.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.androidfmm.R
import com.example.androidfmm.databinding.FragmentSettingsMenuBinding

class SettingsMenu: Fragment() {
    override fun onCreate(savedInstance: Bundle?) {
        super.onCreate(savedInstance)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstance: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentSettingsMenuBinding>(
            inflater, R.layout.fragment_settings_menu, container, false)

        binding.settingsMenu = this

        return binding.root
    }
}
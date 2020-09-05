package com.example.androidfmm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var navView: NavigationView
    private lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbarView: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbarView)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.actionbar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.nav_create_new_alarm -> {
            Navigation.findNavController(this, R.id.nav_host_fragment).navigate(R.id.createAlarmFragment)
            Log.i("MENU", "MENU NAV CLICKED")
            true
        }
        else -> {
            Log.i("MENU", "WAT")
            super.onOptionsItemSelected(item)
        }
    }
}
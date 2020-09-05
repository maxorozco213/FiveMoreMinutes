package com.example.androidfmm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val toolbarView: Toolbar = findViewById(R.id.toolbar)

        setSupportActionBar(toolbarView)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setupActionBarWithNavController(findNavController(R.id.nav_host_fragment))
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.nav_host_fragment)
        return navController.navigateUp()
    }
}
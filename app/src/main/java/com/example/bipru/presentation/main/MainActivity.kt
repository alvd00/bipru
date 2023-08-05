package com.example.bipru.presentation.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.bipru.R

class MainActivity : AppCompatActivity() {
    private val navController by lazy {
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.hostFragment) as NavHostFragment
        navHostFragment.navController
    }
    private var onDestinationChangedListener: NavController.OnDestinationChangedListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        onDestinationChangedListener?.let {
            navController.addOnDestinationChangedListener(it)
        }
        super.onResume()
    }

    override fun onPause() {
        onDestinationChangedListener?.let {
            navController.removeOnDestinationChangedListener(it)
        }
        super.onPause()
    }
}
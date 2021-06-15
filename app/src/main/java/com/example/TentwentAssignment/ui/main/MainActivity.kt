package com.example.TentwentAssignment.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.TentwentAssignment.R
import com.example.TentwentAssignment.util.FragmentStack
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    val TAG: String = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        FragmentStack.addFragmentToContainer(R.id.main_container, supportFragmentManager,
                MainFragment(), "MainFragment")
    }
}
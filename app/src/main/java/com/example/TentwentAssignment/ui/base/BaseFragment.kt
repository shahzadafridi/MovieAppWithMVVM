package com.example.TentwentAssignment.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

abstract class BaseFragment() : Fragment() {

    var parentActivity: AppCompatActivity? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        parentActivity = activity as AppCompatActivity?

        setNavTitle()

    }

    abstract fun setNavTitle()

}
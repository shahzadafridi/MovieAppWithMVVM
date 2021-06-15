package com.example.TentwentAssignment.util

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

object FragmentStack {

    fun replaceFragmentToContainer(container: Int, fragmentManager: FragmentManager, fragment: Fragment, tag: String){
        val transaction = fragmentManager.beginTransaction()
        transaction.replace(container, fragment, tag)
        transaction.addToBackStack(tag)
        transaction.commit()
    }

    fun addFragmentToContainer(container: Int, fragmentManager: FragmentManager, fragment: Fragment, tag: String){
        val transaction = fragmentManager.beginTransaction()
        if (fragmentManager.findFragmentByTag(tag) == null) {
            transaction.add(container, fragment, tag)
            transaction.addToBackStack(tag)
            transaction.commit()
        } else {
            transaction.show(fragmentManager.findFragmentByTag(tag)!!).commit()
        }
    }
}

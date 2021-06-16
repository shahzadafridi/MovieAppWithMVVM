package com.example.TentwentAssignment.ui.movie.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.TentwentAssignment.R
import com.example.TentwentAssignment.databinding.FragmentMovieBookBinding
import com.example.TentwentAssignment.ui.base.BaseFragment

class MovieBookFragment : BaseFragment() {

    val TAG: String = "MovieBookFragment"
    lateinit var binding: FragmentMovieBookBinding

    override fun setNavTitle() {
         parentActivity!!.title = "Booking"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMovieBookBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}
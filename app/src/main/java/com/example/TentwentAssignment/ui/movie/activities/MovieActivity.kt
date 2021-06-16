package com.example.TentwentAssignment.ui.movie.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.TentwentAssignment.R
import com.example.TentwentAssignment.databinding.ActivityMovieBinding
import com.example.TentwentAssignment.ui.movie.fragments.MovieListingFragment
import com.example.TentwentAssignment.util.FragmentStack
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieActivity : AppCompatActivity() {

    val TAG: String = "MovieActivity"
    lateinit var binding: ActivityMovieBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)

        FragmentStack.addFragmentToContainer(R.id.main_container, supportFragmentManager,
                MovieListingFragment(), "MovieListingFragment")
    }
}
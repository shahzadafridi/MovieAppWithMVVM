package com.example.TentwentAssignment.ui.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.TentwentAssignment.data.remote.response.MovieResponse
import com.example.TentwentAssignment.data.remote.response.Result
import com.example.TentwentAssignment.databinding.FragmentMainBinding
import com.example.TentwentAssignment.ui.base.BaseFragment
import com.example.TentwentAssignment.util.Resource
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainFragment : BaseFragment(), MovieAdapter.IMovie {

    val TAG: String = "MainFragment"
    val viewModel: MainViewModel by viewModels()
    @Inject lateinit var gson: Gson
    lateinit var binding: FragmentMainBinding

    override fun setNavTitle() {
        parentActivity!!.title = "Movie Listing"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Call API
        viewModel.onGetMovies()
        viewModel.moviesLiveData.observe(viewLifecycleOwner, {
            when (it.status) {
                Resource.Status.LOADING -> {
                    Log.d(TAG, "LOADING ${it.message}")
                    binding.progressBar.visibility = View.VISIBLE
                }
                Resource.Status.SUCCESS -> {
                    Log.i(TAG, "SUCCESS ${it.data}")
                    it.data?.let { movieEntity ->
                        val moviesResponse = gson.fromJson(movieEntity.response,MovieResponse::class.java)
                        binding.movieRv.adapter =  MovieAdapter(requireContext(),this@MainFragment,moviesResponse.results)
                        binding.progressBar.visibility = View.GONE
                    }
                }
                Resource.Status.ERROR -> {
                    Log.e(TAG, "ERROR ${it.message}")
                    binding.progressBar.visibility = View.GONE
                }
                else -> {
                    Log.e(TAG,"INVALID - No status found.")
                    binding.progressBar.visibility = View.GONE
                }
            }
        })

    }

    override fun onMovieClick(item: Result, position: Int) {

    }

}
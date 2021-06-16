package com.example.TentwentAssignment.ui.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.example.TentwentAssignment.R
import com.example.TentwentAssignment.data.remote.response.movie.detail.MovieDetailResponse
import com.example.TentwentAssignment.data.remote.response.movie.images.ImagesResponse
import com.example.TentwentAssignment.databinding.FragmentMovieDetailBinding
import com.example.TentwentAssignment.ui.base.BaseFragment
import com.example.TentwentAssignment.util.Constants
import com.example.TentwentAssignment.util.FragmentStack
import com.example.TentwentAssignment.util.Resource
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MovieDetailFragment : BaseFragment() {

    val TAG: String = "MovieDetailFragment"
    lateinit var binding: FragmentMovieDetailBinding
    val viewModel: MainViewModel by viewModels()
    @Inject lateinit var gson: Gson

    override fun setNavTitle() {

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieDetailBinding.inflate(layoutInflater)
        viewModel.moviesDetailLiveData.observe(viewLifecycleOwner,{
            when (it.status) {
                Resource.Status.LOADING -> {
                    Log.d(TAG, "LOADING ${it.message}")
                }
                Resource.Status.SUCCESS -> {
                    Log.i(TAG, "SUCCESS ${it.data}")
                    it.data?.let { movieDetailEntity ->
                        val moviesDetailResponse = gson.fromJson(movieDetailEntity.response, MovieDetailResponse::class.java)
                        moviesDetailResponse?.let { m ->
                            binding.movieDetailTitle.text = m.title
                            binding.movieDetailGenre.text = "Test"
                            binding.movieDetailDate.text = m.release_date
                            binding.movieDetailOverview.text = m.overview
                            binding.movieWatchBt.setOnClickListener {
                                goWatchTrailerFragment(m.id)
                            }
                        }
                    }
                }
                Resource.Status.ERROR -> {
                    Log.e(TAG, "ERROR ${it.message}")
                }
                else -> {
                    Log.e(TAG,"INVALID - No status found.")
                }
            }
        })

        viewModel.imageLiveData.observe(viewLifecycleOwner,{
            when (it.status) {
                Resource.Status.LOADING -> {
                    Log.d(TAG, "LOADING ${it.message}")
                }
                Resource.Status.SUCCESS -> {
                    Log.i(TAG, "SUCCESS ${it.data}")
                    it.data?.let { imageEntity ->
                        val imageResponse = gson.fromJson(imageEntity.response, ImagesResponse::class.java)
                        imageResponse?.let { images ->
                            if(!images.posters.isNullOrEmpty()) {
                                val imageList = ArrayList<SlideModel>()
                                images.posters.forEachIndexed { index, poster ->
                                    if (index < 5) {
                                        imageList.add(
                                            SlideModel(
                                                imageUrl = Constants.IMAGE_URL + poster.file_path,
                                                scaleType = ScaleTypes.FIT
                                            )
                                        )
                                    }
                                }
                                binding.movieDetailImage.setImageList(imageList)
                            }
                        }
                    }
                }
                Resource.Status.ERROR -> {
                    Log.e(TAG, "ERROR ${it.message}")
                }
                else -> {
                    Log.e(TAG,"INVALID - No status found.")
                }
            }
        })
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let { bundle ->
            val movieId = bundle.getInt("movie_id")
            viewModel.onImage(
                apiKey = Constants.API_KEY,
                movieId = movieId
            )
            viewModel.onGetMovieDetail(
                apiKey = Constants.API_KEY,
                movieId = movieId
            )
        }
    }

    private fun goWatchTrailerFragment(movieId: Int){
        val intent = Intent(parentActivity!!,WatchActivity::class.java).apply {
            putExtra("movie_id",movieId)
        }
        parentActivity!!.startActivity(intent)
    }
}
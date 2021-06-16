package com.example.TentwentAssignment.ui.main

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.TentwentAssignment.data.remote.response.movie.video.VideoResponse
import com.example.TentwentAssignment.databinding.ActivityWatchBinding
import com.example.TentwentAssignment.util.Constants
import com.example.TentwentAssignment.util.Resource
import com.google.gson.Gson
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class WatchActivity : AppCompatActivity(){

    val TAG: String = "WatchActivity"
    lateinit var binding: ActivityWatchBinding
    val viewModel: MainViewModel by viewModels()
    @Inject lateinit var gson: Gson

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWatchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.watchBack.setOnClickListener {
            onBackPressed()
        }
        val movieId = intent.getIntExtra("movie_id", -1)
        if (movieId > 0) {
            viewModel.onVideo(
                apiKey = Constants.API_KEY,
                movieId = movieId
            )
        }
        viewModel.videoLiveData.observe(this, {
            when (it.status) {
                Resource.Status.LOADING -> {
                    Log.d(TAG, "LOADING ${it.message}")
                }
                Resource.Status.SUCCESS -> {
                    Log.i(TAG, "SUCCESS ${it.data}")
                    it.data?.let { videoEntity ->
                        val videoResponse = gson.fromJson(videoEntity.response, VideoResponse::class.java)
                        videoResponse?.let { video ->
                          onPlayVideo(video.results[0].key)
                        }
                    }
                }
                Resource.Status.ERROR -> {
                    Log.e(TAG, "ERROR ${it.message}")
                }
                else -> {
                    Log.e(TAG, "INVALID - No status found.")
                }
            }
        })
    }

    fun onPlayVideo(videoId: String){
        lifecycle.addObserver(binding.youtubePlayerView)
        binding.youtubePlayerView.addYouTubePlayerListener(object :
            AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                youTubePlayer.loadVideo(videoId, 0F)
            }
        })
    }

}
package com.example.TentwentAssignment.ui.movie.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.TentwentAssignment.R
import com.example.TentwentAssignment.databinding.FragmentMovieBookBinding
import com.example.TentwentAssignment.ui.base.BaseFragment
import com.example.TentwentAssignment.util.Constants

class MovieBookFragment : BaseFragment() {

    val TAG: String = "MovieBookFragment"
    lateinit var binding: FragmentMovieBookBinding

    override fun setNavTitle() {
         parentActivity!!.title = getString(R.string.movie_book_frag_title)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieBookBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val spinnerLocationsAdapter: ArrayAdapter<String> = ArrayAdapter<String>(
            requireContext(), android.R.layout.simple_spinner_item, Constants.locations)
        spinnerLocationsAdapter.setDropDownViewResource(R.layout.row_spinner_layout)
        binding.movieBookLocations.adapter = spinnerLocationsAdapter

        val spinnerCinemaAdapter: ArrayAdapter<String> = ArrayAdapter<String>(
            requireContext(), android.R.layout.simple_spinner_item, Constants.cinemas)
        spinnerCinemaAdapter.setDropDownViewResource(R.layout.row_spinner_layout)
        binding.movieBookCinemas.adapter = spinnerCinemaAdapter

        binding.movieBookBt.setOnClickListener {
            if(binding.movieBookSeat.text.toString().isEmpty()){
                Toast.makeText(requireContext(),getString(R.string.book_validation_text),Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            Toast.makeText(requireContext(),getString(R.string.book_success_text),Toast.LENGTH_LONG).show()
            parentActivity!!.onBackPressed()
        }
    }

}
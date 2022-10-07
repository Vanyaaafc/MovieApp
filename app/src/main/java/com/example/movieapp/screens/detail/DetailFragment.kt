package com.example.movieapp.screens.detail

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.movieapp.MAIN
import com.example.movieapp.R
import com.example.movieapp.databinding.FragmentDetailBinding
import com.example.movieapp.models.MovieItemModel

class DetailFragment : Fragment() {

    private var mBinding: FragmentDetailBinding?= null
    private val binding get() = mBinding!!
    lateinit var currentMovie: MovieItemModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        mBinding = FragmentDetailBinding.inflate(layoutInflater, container, false)
         currentMovie = arguments?.getSerializable("movie") as MovieItemModel
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    @SuppressLint("SetTextI18n")
    private fun init() {
        val viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        Glide.with(MAIN)
            .load("https://www.themoviedb.org/t/p/w600_and_h900_bestv2${currentMovie.poster_path }")
            .placeholder(R.drawable.ic_launcher_foreground)
            .into(binding.imgDetail)

        binding.toolbar.setNavigationOnClickListener {
            MAIN.navController.popBackStack()
        }
        binding.toolbar.title = currentMovie.title
        binding.tvTitle.text = currentMovie.title
        binding.tvData.text = currentMovie.release_date
        binding.tvDescription.text = "\t${currentMovie.overview}"
    }

    override fun onDestroy() {
        super.onDestroy()
        mBinding = null
    }
}
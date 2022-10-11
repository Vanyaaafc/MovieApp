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
import com.example.movieapp.SaveShared
import com.example.movieapp.databinding.FragmentDetailBinding
import com.example.movieapp.models.MovieItemModel

@Suppress("DEPRECATION")
class DetailFragment : Fragment() {

    private var mBinding: FragmentDetailBinding?= null
    private val binding get() = mBinding!!
    private lateinit var currentMovie: MovieItemModel
    private var isFavorite = false

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
        val valueBool = SaveShared.getFavorite(MAIN, currentMovie.id.toString())
        val viewModel = ViewModelProvider(this)[DetailViewModel::class.java]

        if(isFavorite != valueBool){
            binding.imgDetailFavorite.setImageResource(R.drawable.ic_baseline_favorite_24)
        }else{
            binding.imgDetailFavorite.setImageResource(R.drawable.ic_baseline_favorite_border_24)
        }

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

        binding.imgDetailFavorite.setOnClickListener {
            isFavorite = if(isFavorite == valueBool){
                binding.imgDetailFavorite.setImageResource(R.drawable.ic_baseline_favorite_24)
                SaveShared.setFavorite(MAIN, currentMovie.id.toString(), true)
                viewModel.insert(currentMovie){}
                true
            }else{
                binding.imgDetailFavorite.setImageResource(R.drawable.ic_baseline_favorite_border_24)
                SaveShared.setFavorite(MAIN, currentMovie.id.toString(), false)
                viewModel.delete(currentMovie){}
                false
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mBinding = null
    }
}
package com.example.movieapp.screens.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.movieapp.REALIZATION
import com.example.movieapp.models.MovieItemModel

class FavoriteFragmentViewModel: ViewModel() {
    fun getAllMovies(): LiveData<List<MovieItemModel>>{
        return REALIZATION.allMovies
    }
}
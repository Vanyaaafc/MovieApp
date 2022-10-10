package com.example.movieapp.screens.main

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.movieapp.data.retrofit.RetrofitRepository
import com.example.movieapp.data.room.MoviesRoomDataBase
import com.example.movieapp.data.room.repository.MoviesRepositoryRealisation
import com.example.movieapp.models.MoviesModel
import kotlinx.coroutines.launch
import retrofit2.Response

class MainFragmentViewModel(application: Application): AndroidViewModel(application) {
    private val repository = RetrofitRepository()
    lateinit var realization: MoviesRepositoryRealisation
    val myMovies: MutableLiveData<Response<MoviesModel>> = MutableLiveData()
    val context = application

    fun getMoviesRetrofit() {
        viewModelScope.launch {
            try {
                myMovies.value = repository.getMovies()
            } catch (e: Throwable) {
                Log.e("ERROR", e.message.toString())
            }
        }
    }
    fun initDatabase(){
        val dataMovie = MoviesRoomDataBase.getInstance(context).getMovieDao()
        realization = MoviesRepositoryRealisation(dataMovie)
    }
}
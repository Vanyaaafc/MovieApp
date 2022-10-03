package com.example.movieapp.screens.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.data.retrofit.RetrofitRepository
import com.example.movieapp.models.MoviesModel
import kotlinx.coroutines.launch
import retrofit2.Response
import java.lang.Exception

class MainFragmentViewModel: ViewModel() {
    private val repository = RetrofitRepository()
    val myMovies: MutableLiveData<Response<MoviesModel>> = MutableLiveData()

    fun getMovies() {
        viewModelScope.launch {
            try {
                myMovies.value = repository.getMovies()
            } catch (e: Exception) {
                Log.e("ERROR", e.message.toString())
            }
        }
    }
}
package com.example.movieapp.data.retrofit

import com.example.movieapp.data.retrofit.api.RetrofitInstance
import com.example.movieapp.models.MoviesModel
import retrofit2.Response

class RetrofitRepository {
    suspend fun getMovies(): Response<MoviesModel>{
        return RetrofitInstance.api.getPopularMovie()
    }
}
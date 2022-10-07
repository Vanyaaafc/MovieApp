package com.example.movieapp.data.retrofit.api

import com.example.movieapp.models.MoviesModel
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("3/movie/popular?api_key=bdf19b9e60baf6306e135667eacee5f5&language=ru&page=1")
    suspend fun getPopularMovie(): Response<MoviesModel>
}
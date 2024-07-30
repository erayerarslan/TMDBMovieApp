package com.erayerarslan.tmdbmovieapp.network

import com.erayerarslan.tmdbmovieapp.model.MovieDetailResponse
import com.erayerarslan.tmdbmovieapp.model.MovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface ApiService {
    @GET("popular")

    suspend fun getMovieList(@Header("Authorization") token : String) : Response<MovieResponse>

    @GET("{movie_id}")

    suspend fun  getMovieDetail(@Path("movie_id") movieId: String, @Header("Authorization") token: String) :Response<MovieDetailResponse>
}
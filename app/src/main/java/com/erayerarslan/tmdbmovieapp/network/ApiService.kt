package com.erayerarslan.tmdbmovieapp.network

import com.erayerarslan.tmdbmovieapp.model.MovieDetailResponse
import com.erayerarslan.tmdbmovieapp.model.MovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("popular")

    suspend fun getMovieList(@Header("Authorization") token : String) : Response<MovieResponse>

    @GET("{movie_id}")

    suspend fun  getMovieDetail(@Path("movie_id") movieId: String, @Header("Authorization") token: String) :Response<MovieDetailResponse>

    @GET("popular")

    suspend fun getMovieListFiltered(@Header("Authorization") token : String,
                                     @Query("query") query: String?
                                     ) : Response<MovieResponse>
}


//data class vs class farkı
//retrofit dökümatasyonları okuyalım get,post,put bunların birbilerine olan değişikliker

//path,body,request modellerine bakalım

//yıldıza basınca toast mesaj çıkartmak ödev
//yuvarlama işi 7.1 gibi ama fonksiyonla yazılacak

//fotoğrafa uzun süre basılı tutunca toast mesajı yazılack
package com.erayerarslan.tmdbmovieapp.model


import com.google.gson.annotations.SerializedName

data class MovieDetailResponse(

    @SerializedName("backdrop_path", alternate = ["back_drop_path"])
    val backdropPath: String?,
    @SerializedName("overview")
    val overview: String?,
    @SerializedName("production_companies")
    val productionCompanies: List<ProductionCompany?>?,
    @SerializedName("spoken_languages")
    val spokenLanguages: List<SpokenLanguage?>?,
    @SerializedName("title")
    val title: String?,
   // @SerializedName("video")
   // val video: Boolean?,
    @SerializedName("vote_average")
    val voteAverage: Double?,
)
//data class vs class farkı
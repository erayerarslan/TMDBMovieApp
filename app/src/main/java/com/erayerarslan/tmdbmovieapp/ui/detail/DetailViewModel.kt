package com.erayerarslan.tmdbmovieapp.ui.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.erayerarslan.tmdbmovieapp.model.MovieDetailResponse
import com.erayerarslan.tmdbmovieapp.network.ApiClient
import com.erayerarslan.tmdbmovieapp.util.Constants
import kotlinx.coroutines.launch

class DetailViewModel: ViewModel() {
    val movieResponse: MutableLiveData<MovieDetailResponse> = MutableLiveData()
    val isLoading = MutableLiveData(false)
    val errorMesssage : MutableLiveData<String?> = MutableLiveData()

    fun getMovieDetail(movieId: Int) {
        isLoading.value = true

        viewModelScope.launch {
            try {
                val response = ApiClient.getClient().getMovieDetail(movieId = movieId.toString(), token = Constants.BEARER_TOKEN)
                if (response.isSuccessful){
                    movieResponse.postValue(response.body())
                }else{
                    if (response.message().isNullOrEmpty() ){
                        errorMesssage.value="An Unknown error occorred"
                    }else{
                        errorMesssage.value=response.message()
                    }
                }


            }catch (e: Exception){
                errorMesssage.value=e.message
            }finally {
                isLoading.value = false
            }
        }

    }
}
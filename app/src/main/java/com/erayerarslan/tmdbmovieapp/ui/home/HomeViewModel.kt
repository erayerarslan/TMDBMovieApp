package com.erayerarslan.tmdbmovieapp.ui.home

import android.graphics.Movie
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.erayerarslan.tmdbmovieapp.model.MovieItem
import com.erayerarslan.tmdbmovieapp.network.ApiClient
import com.erayerarslan.tmdbmovieapp.util.Constants
import kotlinx.coroutines.launch

class HomeViewModel: ViewModel() {

    val movieList : MutableLiveData<List<MovieItem?>?> = MutableLiveData()
    val isLoading : MutableLiveData<Boolean> = MutableLiveData(false)
    val errorMessage : MutableLiveData<String?> = MutableLiveData()

    fun getMoviesList(){
        isLoading.value = true
        viewModelScope.launch {
            try {

                val response = ApiClient.getClient().getMovieList(token = Constants.BEARER_TOKEN)


                if (response.isSuccessful){
                    movieList.postValue(response.body()?.movieItems)
                }else{
                    if (response.message().isNullOrEmpty()){
                        errorMessage.value = "Unknown Error"
                    }
                    else{
                        errorMessage.value = response.message()
                    }

                }
            }catch (e: Exception){
                errorMessage.value= e.message
            }
            finally {
                isLoading.value=false
            }
        }
    }

}
package com.erayerarslan.tmdbmovieapp.ui.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.erayerarslan.tmdbmovieapp.model.MovieItem
import com.erayerarslan.tmdbmovieapp.network.ApiClient
import com.erayerarslan.tmdbmovieapp.util.Constants
import kotlinx.coroutines.launch

class SearchViewModel :ViewModel(){
    val movieList : MutableLiveData<List<MovieItem?>?> = MutableLiveData()
    val filteredMovies: MutableLiveData<List<MovieItem?>?> = MutableLiveData()
    val isLoading : MutableLiveData<Boolean> = MutableLiveData(false)
    val errorMessage : MutableLiveData<String?> = MutableLiveData()

    fun fetchMovies(query: String? = null) {
        isLoading.value = true
        viewModelScope.launch {
            try {
                // Query parametresi isteğe bağlıdır, null olabilir
                val response = ApiClient.getClient().getMovieListFiltered(
                    token = Constants.BEARER_TOKEN,
                    query = query // Sorgu parametresi ekleniyor
                )

                if (response.isSuccessful) {
                    val movies = response.body()?.movieItems

                    if (!query.isNullOrEmpty()) {
                        val filteredMovies = movies?.filter {
                            it?.title!!.contains(query, ignoreCase = true)
                        }

                        movieList.postValue(filteredMovies)
                    } else {
                        movieList.postValue(movies)
                    }
                } else {
                    errorMessage.value = response.message() ?: "Unknown Error"
                }
            } catch (e: Exception) {
                errorMessage.value = e.message
            } finally {
                isLoading.value = false
            }
        }
    }



    fun getSearchMovies(){
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
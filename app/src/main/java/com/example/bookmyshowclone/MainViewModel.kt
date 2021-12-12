package com.example.bookmyshowclone

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel(private val networkHelper: NetworkHelper,private val movieRepository: MovieRepository):ViewModel() {
    companion object {
        private const val API_KEY = "ca1f946740735c079c0392f78d18c79a"
    }
    private val _movieresponse = MutableLiveData<MovieResponse>()
    val movieresponse :LiveData<MovieResponse>
    get()= _movieresponse

    private val _erroresponse = MutableLiveData<String>()
    val erroresponse : LiveData<String>
    get() = _erroresponse

    fun onCreate(){
        if(networkHelper.isNetworkConnected()){
            viewModelScope.launch {
                movieRepository.fetchMovies(API_KEY,{movieresponse->
                    _movieresponse.postValue(movieresponse)
                },{
                    _erroresponse.postValue(it)
                })
            }

        }
        else{
            viewModelScope.launch {
                movieRepository.getMoviesLocal {
                        movieResponse ->
                    if (movieResponse!=null && movieResponse.results.isNotEmpty()) {
                        _movieresponse.postValue(movieResponse)
                    }
                    else{
                        _erroresponse.postValue("something went wrong!")
                    }

                }
            }

        }
    }
}
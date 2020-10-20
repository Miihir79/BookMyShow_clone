package com.example.bookmyshowclone

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel(private val networkHelper: NetworkHelper,private val movieRepository: MovieRepository):ViewModel() {
    companion object {
        private const val API_KEY = "ca1f946740735c079c0392f78d18c79a"
    }
    private val _movieresponse = MutableLiveData<MovieResponse>()
    val movieresponse :LiveData<MovieResponse>
    get()= _movieresponse

    private val _erroeresponse = MutableLiveData<String>()
    val erroeresponse : LiveData<String>
    get() = _erroeresponse

    fun onCreate(){
        if(networkHelper.isNetworkConnected()){

            movieRepository.fetchMovies(API_KEY,{movieresponse->
                _movieresponse.postValue(movieresponse)
            },{
                _erroeresponse.postValue(it)
            })

        }
        else{
            movieRepository.getMoviesLocal {
                movieResponse ->
                if (movieResponse!=null && movieResponse.results.isNotEmpty()) {
                    _movieresponse.postValue(movieResponse)
                }
                else{
                    _erroeresponse.postValue("something went wrong!")
                }

            }
        }
    }
}
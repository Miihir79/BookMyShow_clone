package com.example.bookmyshowclone

import com.example.bookmyshowclone.MovieResponse

interface MovieRepository {
    fun fetchMovies(api_key:String, onSuccess:(MovieResponse) -> Unit, onError:(String) -> Unit)
    fun getMoviesLocal(onSuccess: (MovieResponse?) -> Unit)

}
package com.example.bookmyshowclone

import com.example.bookmyshowclone.MovieResponse

interface MovieRepository {
    fun fetchMovies(api_key:String, onSucess:(MovieResponse) -> Unit, onError:(String) -> Unit)
    fun getMoviesLocal(onSucess: (MovieResponse?) -> Unit)

}
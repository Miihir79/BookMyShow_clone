package com.example.bookmyshowclone

interface MovieRepository {
    fun fetchMovies(api_key:String, onSucess:(MovieResponse) -> Unit, onError:(String) -> Unit)
    fun getMoviesLocal(onSucess: (MovieResponse?) -> Unit)

}
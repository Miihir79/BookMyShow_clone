package com.example.bookmyshowclone

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieRepositoryImp(private val movieDao: MovieDao,private val request:MovieService):MovieRepository {
    override fun fetchMovies(api_key: String,onSucess:(MovieResponse) -> Unit, onError:(String) -> Unit) {
       // val request = RetrofitBuilder.buildService()
        val call = request.getMovies(api_key)

        call.enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                //hideProgress()
                if(response.isSuccessful && response.body() != null){
                    Thread{
                        movieDao.insertMovies(response.body()!!)
                        onSucess(response.body()!!)
                    }.start()
                    //showMovies(response.body()!!.results)
                    onSucess(response.body()!!)
                }else{
                    onError(response.message())
                    //showErrormess(resources.getString(R.string.error_msg))
                }

            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
               // hideProgress()
                onError("something went wrong")
               // showErrormess(t.message)

            }

        })
    }

    override fun getMoviesLocal(onSucess: (MovieResponse?) -> Unit) {
        Thread{
            onSucess(movieDao.getmovies())
        }.start()
    }
}
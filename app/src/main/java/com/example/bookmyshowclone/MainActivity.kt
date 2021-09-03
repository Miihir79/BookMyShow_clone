package com.example.bookmyshowclone

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.*
import android.view.View
import android.view.Window
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import com.example.bookmyshowclone.databse.Movie
import com.example.bookmyshowclone.databse.MovieDatabase
import com.example.bookmyshowclone.databse.MovieRepositoryImp
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    companion object {
        private const val API_KEY = "ca1f946740735c079c0392f78d18c79a"
    }
    private lateinit var viewModel: MainViewModel
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        with(window) {
            requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS)

            // setting an exit transition
            exitTransition = Explode()
        }
        setContentView(R.layout.activity_main)

        setupViewModel()
        ObserveViewModel()

    }

    private fun ObserveViewModel(){
        viewModel.movieresponse.observe(this,{
            showMovies(it.results)
            hideProgress()
        })

        viewModel.erroeresponse.observe(this,{
            showErrorMess(it)
            hideProgress()
        })
    }

    private fun setupViewModel(){
        showProgress()
        viewModel = ViewModelProvider(this@MainActivity,ViewmodelFactory(NetworkHelper(this@MainActivity),
            MovieRepositoryImp(MovieDatabase.getInstance(this@MainActivity).movieDao(),RetrofitBuilder.buildService())
        ))[MainViewModel::class.java]
        viewModel.onCreate()

    }

    private fun showMovies(movies: List<Movie>){
        recyclerView.visibility= View.VISIBLE
        progressBar.visibility= View.GONE
        recyclerView.setHasFixedSize(true)
        recyclerView.itemAnimator=DefaultItemAnimator()
        recyclerView.adapter= MovieAdapter(movies,this)


    }
    private fun showProgress(){
        progressBar.visibility = View.VISIBLE
    }
    private fun hideProgress(){
        progressBar.visibility = View.GONE
    }
    private fun showErrorMess(errorMessage: String?){
        errorView.visibility = View.VISIBLE
        errorView.text= errorMessage

    }
}
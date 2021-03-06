package com.example.bookmyshowclone
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    companion object {
        private const val API_KEY = "ca1f946740735c079c0392f78d18c79a"
    }
    private lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
            showErrormess(it)
            hideProgress()
        })
    }

    private fun setupViewModel(){
        showProgress()
        viewModel = ViewModelProvider(this,ViewmodelFactory(NetworkHelper(this),
                MovieRepositoryImp(MovieDatabase.getInstance(this).movieDao(),RetrofitBuilder.buildService())))[MainViewModel::class.java]
        viewModel.onCreate()
    }

    private fun showMovies(movies: List<Movie>){
        recyclerView.visibility= View.VISIBLE
        progressBar.visibility= View.GONE
        recyclerView.setHasFixedSize(true)
        recyclerView.itemAnimator=DefaultItemAnimator()
        recyclerView.adapter= MovieAdapter(movies)


    }
    private fun showProgress(){
        progressBar.visibility = View.VISIBLE
    }
    private fun hideProgress(){
        progressBar.visibility = View.GONE
    }
    private fun showErrormess(erroeMessage: String?){
        errorView.visibility = View.VISIBLE
        errorView.text= erroeMessage

    }
}
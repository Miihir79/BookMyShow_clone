package com.example.bookmyshowclone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.bookmyshowclone.databse.MovieDatabase
import com.example.bookmyshowclone.databse.MovieRepositoryImp
import kotlinx.android.synthetic.main.activity_movie_view.*

class movieView : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_view)
        supportActionBar?.hide()
        val imgUrl = intent.getStringExtra("ImgURL")
        Glide.with(this).load(imgUrl).into(imageView)
        val id = intent.getIntExtra("ID",0)

        viewModel = ViewModelProvider(this,ViewmodelFactory(NetworkHelper(this),
            MovieRepositoryImp(MovieDatabase.getInstance(this).movieDao(),RetrofitBuilder.buildService())
        ))[MainViewModel::class.java]
        viewModel.onCreate()

        viewModel.movieresponse.observe(this,{
            val movies = it.results
            for(i in movies.indices){
                if (id == movies[i].id ){
                    Log.i("TAG", "onCreate: ${movies[i].posterPath}")
                    Glide.with(this).asBitmap().load("https:image.tmdb.org/t/p/w500" +movies[i].posterPath).into(imageView)
                    textDesc.text = movies[i].overview
                    collapsingToolBar.title = movies[i].title
                    toolBar.title = movies[i].title
                }
            }
        })

        imageView2.setOnClickListener {
            finish()
        }
    }
}
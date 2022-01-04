package com.example.bookmyshowclone
import android.app.Activity
import android.app.ActivityOptions
import android.content.Intent
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.bookmyshowclone.databse.Movie
import com.example.bookmyshowclone.ui.movieView
import kotlinx.android.synthetic.main.movie_item_layout.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieAdapter(private val movies:List<Movie>, private val context: Activity): RecyclerView.Adapter<MovieAdapter.MoviesViewHolder>() {

     override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_item_layout,parent,false)
         return MoviesViewHolder(view)
     }

     @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
     override fun onBindViewHolder(holder: MoviesViewHolder, position: Int){
         holder.itemView.setOnClickListener {
             val intent = Intent(holder.itemView.context, movieView::class.java)
             intent.putExtra("ImgURL",movies[position].posterPath)
             intent.putExtra("ID",movies[position].id)
             it.context.startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(context).toBundle())
         }
         return holder.bind(movies[position])
     }


     override fun getItemCount(): Int = movies.count()

     class MoviesViewHolder(itemview: View): RecyclerView.ViewHolder(itemview) {

         companion object{
             private const val IMAGE_BASE_URL = "https:image.tmdb.org/t/p/w500"
         }
         fun bind (movie: Movie){
             CoroutineScope(Dispatchers.Main).launch {
                 itemView.movieTitle.text = movie.title
                 itemView.releaseDate.text = movie.releaseDate
                 itemView.avgVoting.text = movie.voteAverage.toString()
                 Glide.with(itemView.context).load(IMAGE_BASE_URL+movie.posterPath).into(itemView.moviePoster)
             }

         }

     }

 }


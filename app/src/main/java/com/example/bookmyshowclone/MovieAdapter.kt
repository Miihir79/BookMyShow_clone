package com.example.bookmyshowclone
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.bookmyshowclone.databse.Movie
import kotlinx.android.synthetic.main.movie_item_layout.view.*



 class MovieAdapter(private val movies:List<Movie>): RecyclerView.Adapter<MovieAdapter.MoviesViewHolder>() {

     override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_item_layout,parent,false)
         return MoviesViewHolder(view)
     }

     override fun onBindViewHolder(holder: MoviesViewHolder, position: Int)
        = holder.bind(movies[position])

     override fun getItemCount(): Int = movies.count()

     class MoviesViewHolder(itemview: View): RecyclerView.ViewHolder(itemview) {
         companion object{
             private const val IMAGE_BASE_URL = "https:image.tmdb.org/t/p/w500"
         }
         fun bind (movie: Movie){
             itemView.movieTitle.text = movie.title
             itemView.releaseDate.text = movie.releaseDate
             itemView.avgVoting.text = movie.voteAverage.toString()
             itemView.totalVotes.text = movie.voteCount.toString()
             itemView.Adult.text = if(movie.adult ) "A" else "NA"
             Glide.with(itemView.context).load(IMAGE_BASE_URL+movie.posterPath).into(itemView.moviePoster)

         }

     }

 }


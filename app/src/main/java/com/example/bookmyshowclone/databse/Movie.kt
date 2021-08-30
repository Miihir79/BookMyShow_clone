package com.example.bookmyshowclone.databse
import com.google.gson.annotations.SerializedName
data class     Movie(
    val id : Int,
    @SerializedName("poster_path")
     val posterPath: String,

    @SerializedName("release_date")
     val releaseDate: String,

    val title: String,

    @SerializedName("vote_average")
     val voteAverage: Double,

    @SerializedName("vote_count")
     val voteCount: Int,

    val adult : Boolean,

    val original_language: String,

    val popularity: Float,

    val overview: String
)



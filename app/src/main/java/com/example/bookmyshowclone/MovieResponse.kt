package com.example.bookmyshowclone

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.bookmyshowclone.databse.Movie

@Entity(tableName = "tbl_movie_data")
data class MovieResponse(
        @PrimaryKey
        val page: Int = 1,

        @ColumnInfo(name = "movie_response")
        val results: List<Movie>
)
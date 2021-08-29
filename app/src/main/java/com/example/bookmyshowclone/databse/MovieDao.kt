package com.example.bookmyshowclone.databse

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.bookmyshowclone.MovieResponse

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(movieResponse: MovieResponse)
    @Query(value = "select* from tbl_movie_data")
    fun getmovies(): MovieResponse
}
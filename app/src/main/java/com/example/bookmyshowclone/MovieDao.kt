package com.example.bookmyshowclone

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(movieResponse: MovieResponse)
    @Query(value = "select* from tbl_movie_data")
    fun getmovies():MovieResponse
}
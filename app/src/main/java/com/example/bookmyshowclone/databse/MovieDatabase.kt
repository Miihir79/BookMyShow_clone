package com.example.bookmyshowclone.databse

import android.content.Context
import androidx.room.*
import com.example.bookmyshowclone.MovieResponse

@Database(entities = [MovieResponse::class],version = 1)
@TypeConverters(MovietypeConverter::class)

abstract class MovieDatabase :RoomDatabase(){
abstract fun movieDao(): MovieDao
    companion object{
        private const val DATABASE_NAME="movie-app"
        @Volatile
        private var Instance: MovieDatabase?=null

        fun getInstance(context:Context): MovieDatabase {
            kotlin.synchronized(this){
                var instance : MovieDatabase?= Instance
                if (instance==null){
                    instance= Room.databaseBuilder(context.applicationContext, MovieDatabase::class.java, DATABASE_NAME).build()
                    Instance =instance
                }
                return instance
            }
        }
    }
}
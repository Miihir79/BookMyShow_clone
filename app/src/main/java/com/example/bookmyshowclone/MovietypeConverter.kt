package com.example.bookmyshowclone

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

object MovietypeConverter {
    @TypeConverter
    @JvmStatic
    fun fromList(value:List<Movie>)= Gson().toJson(value)

    @TypeConverter
    @JvmStatic
    fun toList(value:String): List<Movie>{
        val listType: Type = object :TypeToken<List<Movie>>(){}.type
        return Gson().fromJson(value,listType)
    }
}
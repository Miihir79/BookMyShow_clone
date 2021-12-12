package com.example.bookmyshowclone

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

@Suppress("UNCHECKED_CAST")
class ViewmodelFactory (private val networkHelper: NetworkHelper,private val movieRepository: MovieRepository):ViewModelProvider.NewInstanceFactory(){

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(networkHelper,movieRepository) as T
    }
}
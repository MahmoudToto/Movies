package com.example.movies.RemoteDB.MoviesPopular

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movies.Pojo.Movies.Result


class MoviesLatestViewModel :ViewModel(){
    val movieRepo :MoviesLatestRepo by lazy { MoviesLatestRepo() }
    fun getLatestMovies():MutableLiveData<List<Result>>{
        return  movieRepo.getLatestMovies()
    }
}
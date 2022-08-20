package com.example.movies.RemoteDB.MoviesTopRated

import android.app.Application
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movies.Pojo.Movies.Movies
import com.example.movies.Pojo.Movies.Result
import com.example.movies.Pojo.MoviesId.MoviesID

class MoviesNowPlayingViewModel:ViewModel() {
    private val myRepo: MoviesNowPlayingRepo by lazy { MoviesNowPlayingRepo() }

    fun getNowPlayingMovies():MutableLiveData<List<Result>>{
        return  myRepo.getNowPlayingMovies()
    }

}
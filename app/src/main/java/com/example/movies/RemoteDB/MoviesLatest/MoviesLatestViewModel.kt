package com.example.movies.RemoteDB.MoviesPopular

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movies.Pojo.LatestMovies.LatestMovies
import com.example.movies.Pojo.Movies.Movies
import com.example.movies.Pojo.Movies.Result
import com.example.movies.RemoteDB.MoviesLatest.RecommendationRepo


class MoviesLatestViewModel :ViewModel(){
    val movieRepo :RecommendationRepo by lazy { RecommendationRepo() }
//

    fun getUpcommingMovie(): MutableLiveData<List<Result>> {
        return  movieRepo.getUpCommingMovie()
    }
}
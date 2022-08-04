package com.example.movies.Details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movies.Pojo.MoviesId.MoviesID

class DetailsViewModel : ViewModel() {
    val movieRepo = DetailsRepo()

    private val _mutable = MutableLiveData<Boolean>()
    val mutable: LiveData<Boolean> = _mutable
    fun getMoviesID(movie: Int): MutableLiveData<MoviesID> {
        return movieRepo.getMoviebyId(movie)
    }

    fun getResult() {
        _mutable.postValue(movieRepo.checkResult())
    }
}
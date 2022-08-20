package com.example.movies.FragmentsUser.FragmentProfile

import android.app.Application
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movies.Pojo.Movies.Movies
import com.example.movies.Pojo.Movies.Result
import com.example.movies.Pojo.MoviesId.MoviesID
import com.example.movies.Pojo.User

class UserViewModel:ViewModel() {
    private val myRepo: ProfileRepo by lazy { ProfileRepo() }

    fun getTopRatedMovies():MutableLiveData<User>{
        return  myRepo.getInfoUSer()
    }


}
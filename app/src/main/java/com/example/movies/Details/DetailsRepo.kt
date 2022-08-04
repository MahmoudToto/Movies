package com.example.movies.Details

import android.content.Intent
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.movies.Adapters.MoviesTopRatedAdapter
import com.example.movies.LocalDB.BaseApplication
import com.example.movies.Pojo.MoviesId.MoviesID
import com.example.movies.RemoteDB.Builder
import com.example.movies.RemoteDB.UserInterFace
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailsRepo {
    private val moviesTopRatedAdapter = MoviesTopRatedAdapter()
    fun getMoviebyId(movie: Int): MutableLiveData<MoviesID> {
        var mutable = MutableLiveData<MoviesID>()
        val interBuilder: UserInterFace = Builder.retorfitBuilder.create(UserInterFace::class.java)
        val call = interBuilder.getMovieByID(movie)
        call.enqueue(object : Callback<MoviesID> {
            override fun onResponse(call: Call<MoviesID>, response: Response<MoviesID>) {
                if (response.isSuccessful)
                    mutable.postValue(response.body())
            }

            override fun onFailure(call: Call<MoviesID>, t: Throwable) {
                Log.d("Error", t.message.toString())
            }

        })
        return mutable
    }

    fun checkResult(): Boolean {
        var mutable = false

        moviesTopRatedAdapter.setOnItemClick(object : MoviesTopRatedAdapter.SentDetails {
            override fun onItemClick(id: Int) {
                Log.d("Getid",id.toString())

            }

            override fun getClickedFavourite(postion: Int) {

            }

        })
        return mutable
    }
}

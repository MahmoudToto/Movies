package com.example.movies.RemoteDB.MoviesLatest

import androidx.lifecycle.MutableLiveData
import com.example.movies.LocalDB.BaseApplication
import com.example.movies.Pojo.Movies.Movies
import com.example.movies.Pojo.Movies.Result
import com.example.movies.RemoteDB.Builder
import com.example.movies.RemoteDB.UserInterFace
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RecommendationRepo {



    fun getUpCommingMovie(): MutableLiveData<List<Result>> {
        val mutableLiveData = MutableLiveData<List<Result>>()
        val userInterfacebuilder = Builder.retorfitBuilder.create(UserInterFace::class.java)
        val call = userInterfacebuilder.getUpCommingMovies()

        call.enqueue(object : Callback<Movies> {
            override fun onResponse(call: Call<Movies>, response: Response<Movies>) {
                if (response.isSuccessful) {
                    mutableLiveData.postValue(response.body()!!.results)
                   BaseApplication.getDatabase()?.getDao()?.insertMovies(response.body()!!.results)
                }
            }

            override fun onFailure(call: Call<Movies>, t: Throwable) {
                mutableLiveData.postValue(BaseApplication.db!!.getDao().getMovies())
            }
        })
        return mutableLiveData
    }

}
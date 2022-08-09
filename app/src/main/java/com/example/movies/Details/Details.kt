package com.example.movies.Details

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.example.movies.Adapters.MoviesLatestAdapter
import com.example.movies.Adapters.MoviesTopRatedAdapter
import com.example.movies.FragmentsUser.FragmentHome
import com.example.movies.LocalDB.BaseApplication
import com.example.movies.MainActivity
import com.example.movies.Pojo.Const
import com.example.movies.Pojo.LatestMovies.LatestMovies
import com.example.movies.Pojo.Movies.Movies
import com.example.movies.Pojo.Movies.Result
import com.example.movies.R
import com.example.movies.RemoteDB.MoviesPopular.MoviesLatestViewModel
import com.example.movies.databinding.ActivityDetailsBinding
import com.example.movies.showToast

class Details : AppCompatActivity() {
    private lateinit var binding: ActivityDetailsBinding
    private val movieLatest = MoviesLatestViewModel()
    private val moviesLatestAdapter by lazy { MoviesLatestAdapter() }
    val moviesID = DetailsViewModel()
    var catId: Int? = null
    var result: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        catId = intent.getIntExtra("id", 78)

        getMoviesByID()
        favouriteMovies()
        selectedButton()
        getLatestMovie()
    }

    fun getMoviesByID() {
        moviesID.getMoviesID(catId!!).observe(this) {
            binding.apply {
                Glide.with(baseContext).load(Const.BASE_URL_IMG + it.poster_path)
                    .into(idMovies)
                topratedMovies.text = it.release_date
                overvieMovie.text = it.overview
                overvieMovie.movementMethod = ScrollingMovementMethod()
            }
        }
    }

    fun favouriteMovies() {
        binding.imgFav.setOnClickListener {
            if (BaseApplication.db?.getDao()?.search(catId!!) == true) {
                BaseApplication.db?.getDao()?.deleteMovieFavourite(catId!!)
                showToast(applicationContext, "Remove Movie ")
                binding.imgFav.setImageResource(R.drawable.ic_heart)
                result = false
            } else {

                BaseApplication.db?.getDao()?.insertMoviesFav(catId!!)
                showToast(applicationContext, "Movie Added")
                binding.imgFav.setImageResource(R.drawable.ic_heartclicked)
                result = true
            }

        }
    }

    fun selectedButton() {
        binding.apply {
            watchId.setOnClickListener {
                showToast(applicationContext, "We will add this soon")
            }
            backIcon.setOnClickListener {
                startActivity(Intent(applicationContext, MainActivity::class.java))
            }
        }
    }

    fun getLatestMovie() {
        movieLatest.getUpcommingMovie().observe(this) {
        Log.d("Lateset", it[0].title)


       sendDataToAdapter(it)
        }
    }

    fun sendDataToAdapter(mList: List<Result>) {
        moviesLatestAdapter.setList(mList)
        binding.recReco.adapter = moviesLatestAdapter
        binding.recReco.layoutManager = GridLayoutManager(applicationContext,2)
    }
}
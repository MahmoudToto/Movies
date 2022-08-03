package com.example.movies.Details

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import com.bumptech.glide.Glide
import com.example.movies.FragmentsUser.FragmentHome
import com.example.movies.LocalDB.BaseApplication
import com.example.movies.Pojo.Const
import com.example.movies.R
import com.example.movies.databinding.ActivityDetailsBinding
import com.example.movies.showToast

class Details : AppCompatActivity() {
    private lateinit var binding: ActivityDetailsBinding
    val moviesID = DetailsViewModel()
    var catId: Int? = null
    var result:Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        catId = intent.getIntExtra("id", 78)

        result = BaseApplication.db?.getDao()?.search(catId!!)!!
        if(result==true){
            binding.imgFav.setImageResource(R.drawable.ic_heartclicked)
        }else{
            binding.imgFav.setImageResource(R.drawable.ic_heart)
        }
        getMoviesByID()
       favouriteMovies()
        binding.watchId.setOnClickListener {
            showToast(applicationContext, "We will add this soon")
        }

        binding.backIcon.setOnClickListener {
            startActivity(Intent(applicationContext, FragmentHome::class.java))
        }
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
            if (result==true){
                BaseApplication.db?.getDao()?.deleteMovieFavourite(catId!!)
                showToast(applicationContext, "Remove Movie ")
                binding.imgFav.setImageResource(R.drawable.ic_heart)
            }else{
                BaseApplication.db?.getDao()?.insertMoviesFav(catId!!)
                showToast(applicationContext, "Movie Added")
                binding.imgFav.setImageResource(R.drawable.ic_heartclicked)
            }

        }
    }
}
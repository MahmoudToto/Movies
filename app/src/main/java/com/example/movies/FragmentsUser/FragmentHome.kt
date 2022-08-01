package com.example.movies.FragmentsUser

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import com.example.movies.Adapters.MoviesPopularAdapter
import com.example.movies.Adapters.MoviesTopRatedAdapter
import com.example.movies.Details.Details
import com.example.movies.FragmentsUser.FragmentProfile.UserViewModel
import com.example.movies.LocalDB.BaseApplication
import com.example.movies.Pojo.Movies.Result
import com.example.movies.Pojo.User
import com.example.movies.RemoteDB.MoviesPopular.MoviesPopularViewModel
import com.example.movies.RemoteDB.MoviesTopRated.MoviesViewModel
import com.example.movies.databinding.FragmentHomeBinding
import com.example.movies.showToast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase


class FragmentHome : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val moviesTopRatedAdapter by lazy { MoviesTopRatedAdapter() }
    private val moviesPopularAdapter by lazy { MoviesPopularAdapter() }
    private var moviePopularViewmodel = MoviesPopularViewModel()
    private var movieTopRatedViewmodel = MoviesViewModel()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        binding = FragmentHomeBinding.inflate(inflater, container, false)


        Log.d("Test", FirebaseAuth.getInstance().currentUser?.email.toString())
        getTopRatedMovies()
        getIdFromTopRatedMovies()
        getPopularMovies()
        getIdFromPopularMovies()

        return binding.root
    }

    private fun getTopRatedMovies() {
        movieTopRatedViewmodel.getTopRatedMovies().observe(viewLifecycleOwner) {
            sentDataToRecyclerviewTopRated(it)
        }
    }

    private fun sentDataToRecyclerviewTopRated(list: List<Result>) {
        moviesTopRatedAdapter.setList(list)
        binding.recMovies.adapter = moviesTopRatedAdapter
    }

    private fun getPopularMovies() {
        moviePopularViewmodel.getPopularMovies().observe(viewLifecycleOwner) {
            sentDataToRecyclerviewPopular(it)
        }
    }

    private fun sentDataToRecyclerviewPopular(list: List<Result>) {
        moviesPopularAdapter.setList(list)
        binding.recMoviesPopular.adapter = moviesPopularAdapter
    }

    private fun getIdFromTopRatedMovies() {
        moviesTopRatedAdapter.setOnItemClick(object : MoviesTopRatedAdapter.SentDetails {
            override fun onItemClick(id: Int) {
                val intent = Intent(requireContext(), Details::class.java)
                intent.putExtra("id", id)
                startActivity(intent)
            }

            override fun getClickedFavourite(postion: Int) {
                var state = BaseApplication.db?.getDao()?.search(postion)
                if (state == true) {
                    showToast(requireContext(), "Already Exists")
                    return
                } else {
                    BaseApplication.db?.getDao()?.insertMoviesFav(postion)
                    showToast(requireContext(), "Movie Added")
                }
            }

        })
    }

    private fun getIdFromPopularMovies() {
        moviesPopularAdapter.setOnItemClick(object : MoviesPopularAdapter.SentDetails {

            override fun onItemClick(id: Int) {
                val intent = Intent(requireContext(), Details::class.java)
                intent.putExtra("id", id)
                startActivity(intent)
            }

            override fun getClickedFavourite(postion: Int) {
                Log.d("idk", id.toString())
                var state = BaseApplication.db?.getDao()?.search(postion)
                if (state == true) {
                    showToast(requireContext(), "Already Exists")
                    return
                } else {
                    BaseApplication.db?.getDao()?.insertMoviesFav(postion)
                    showToast(requireContext(), "Movie Added")
                }
            }


        })

    }


}
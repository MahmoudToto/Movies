package com.example.movies.FragmentsUser.FragmentProfile

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.movies.Pojo.User
import com.example.movies.databinding.FragmentProfileBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase

class FragmentProfile : Fragment() {
    private lateinit var binding: FragmentProfileBinding
    var profileViewModel = UserViewModel()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        getInfoFromRemoteData()
        return binding.getRoot()


    }

    fun getInfoFromRemoteData() {
        profileViewModel.getTopRatedMovies().observe(viewLifecycleOwner) {

            Log.d("Error", it.email)
            getDataToUI(it)
        }
    }

    fun getDataToUI(user: User) {
        binding.apply {
            profTvLocation.text = user.location
            profTvPhone.text = user.number
            profTvEmail.text = user.email
        }
    }


}
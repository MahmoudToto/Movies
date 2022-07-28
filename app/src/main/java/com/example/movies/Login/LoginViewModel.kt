package com.example.movies.Login

import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movies.MainActivity
import com.example.movies.Register.RegisterActivity

class LoginViewModel: ViewModel() {
    val repo = LoginRepo()
    private val _mutable   = MutableLiveData<Boolean>()
    var mutable :LiveData<Boolean> = _mutable
    fun  logInFirebase(email:String , password : String ){
        _mutable.postValue(repo.logIn(email,password))
    }



}
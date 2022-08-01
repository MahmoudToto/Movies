package com.example.movies

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.movies.Login.LoginActivity
import com.example.movies.Register.RegisterActivity
import com.google.firebase.auth.FirebaseAuth


class SplachScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splach_screen)
        val user = FirebaseAuth.getInstance().currentUser

        if(user==null){

            startActivity(Intent(applicationContext,LoginActivity::class.java))
        }else{
            startActivity(Intent(applicationContext,MainActivity::class.java))

        }
    }
}
package com.example.movies

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.movies.Register.RegisterActivity
import com.google.firebase.auth.FirebaseAuth

class SplachScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splach_screen)

        if(FirebaseAuth.getInstance().currentUser==null){
            startActivity(Intent(applicationContext,RegisterActivity::class.java))
        }else{
            startActivity(Intent(applicationContext,MainActivity::class.java))

        }
    }
}
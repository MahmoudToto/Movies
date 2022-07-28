package com.example.movies.Login

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.movies.Adapters.MoviesPopularAdapter
import com.example.movies.MainActivity
import com.example.movies.Pojo.User
import com.example.movies.Register.RegisterActivity
import com.example.movies.databinding.ActivityLoginBinding
import com.example.movies.showToast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase


class LoginActivity : AppCompatActivity() {
    val loginviewmodel by lazy { LoginViewModel() }

    private lateinit var binding: ActivityLoginBinding
    var pressed = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.loginTvRegister.setOnClickListener {
            startActivity(Intent(applicationContext, RegisterActivity::class.java))
        }

        binding.loginBtnLog.setOnClickListener {
            binding.apply {
                val email = loginTvEmail.text.toString()
                val passsword = loginTvPassword.text.toString()
                loginviewmodel.logInFirebase(email, passsword)
                getToken()
            }
        }
    }

    private fun getToken() {
        loginviewmodel.mutable.observe(this) {
            if (it == true) {
                startActivity(Intent(this, MainActivity::class.java))
            } else {
                showToast(baseContext, "Login failed")
            }
        }
    }

    override fun onBackPressed() {
        if (pressed) {
            super.onBackPressed()
        } else {
            return
        }
    }


}
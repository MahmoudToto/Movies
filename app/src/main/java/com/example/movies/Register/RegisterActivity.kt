package com.example.movies.Register

import android.app.Application
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.movies.Login.LoginActivity
import com.example.movies.Pojo.User
import com.example.movies.databinding.ActivityRegisterBinding
import com.example.movies.showToast
import com.google.firebase.auth.FirebaseAuth


class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    var pressed = false
    var registerViewModel = RegisterViewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.registerTvLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

        binding.registerBtn.setOnClickListener {
            binding.apply {
                var name = registerTvName.text.toString()
                var email = registerTvEmail.text.toString()
                var phone = registerTvPhone.text.toString()
                var password = registerTvPassword.text.toString()
                var location = registerTvLocation.text.toString()
                val currentUserId = FirebaseAuth.getInstance().currentUser?.uid.toString()
                val user = User(id = currentUserId, name, email, phone, password, location)
                registerViewModel.Registeration(user)
                getToken()

            }

        }

    }

    fun getToken() {
        registerViewModel.mutable.observe(this) {
            if (it == true) {
                startActivity(Intent(this, LoginActivity::class.java))
            } else {
                showToast(applicationContext, "Registeration failed")
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
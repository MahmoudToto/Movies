package com.example.movies.Login

import com.google.firebase.auth.FirebaseAuth

class LoginRepo() {
    fun logIn(email: String, password: String) :Boolean{
        var state  = true
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (!it.isSuccessful) {
                    println(it.exception?.message)
                   return@addOnCompleteListener

                }
            }
return state
    }




}
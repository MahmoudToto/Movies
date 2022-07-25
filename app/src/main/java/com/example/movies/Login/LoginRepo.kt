package com.example.movies.Login

import com.google.firebase.auth.FirebaseAuth

class LoginRepo(var sentState: state) {

    fun logIn(email: String, password: String) {
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    sentState.success()

                } else {
                    sentState.error(it.exception.toString())
                    println(it.exception?.message)
                }
            }

    }

    interface state {
        fun success()
        fun error(name: String)
    }


}
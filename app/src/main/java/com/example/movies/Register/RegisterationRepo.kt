package com.example.movies.Register

import androidx.lifecycle.MutableLiveData
import com.example.movies.Pojo.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase


class RegisterationRepo {

    fun createUserWithEmailAndPassword(user: User): Boolean {
        val result = true
        try {
            FirebaseAuth
                .getInstance()
                .createUserWithEmailAndPassword(user.email, user.password)
                .addOnCompleteListener {
                    if (!it.isSuccessful) {
                        println("Task Error")
                        println(it.exception?.message)
                        return@addOnCompleteListener
                    }
                }


        } catch (e: Exception) {
            println(e.message ?: e.toString())
            return false
        }

        uploadUserData(user)
        return result
    }

    private fun uploadUserData(user: User) {
        val dataRef = FirebaseDatabase.getInstance().getReference("Users")
        user.id = FirebaseAuth.getInstance().currentUser?.uid.toString()
        dataRef.child(FirebaseAuth.getInstance().currentUser?.uid.toString()).setValue(user)
    }

}



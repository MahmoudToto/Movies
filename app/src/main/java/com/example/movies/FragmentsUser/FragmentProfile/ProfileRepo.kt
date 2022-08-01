package com.example.movies.FragmentsUser.FragmentProfile

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.movies.Pojo.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*


class ProfileRepo {


    fun getInfoUSer():MutableLiveData<User>{
        var mutable  = MutableLiveData<User>()
       FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().currentUser?.uid.toString())
                .addListenerForSingleValueEvent(object : ValueEventListener{
                    override fun onDataChange(snapshot: DataSnapshot) {
                   if(snapshot.exists()==null){
                       Log.d("Error","Null Data From Firebase")
                       return
                   }else{

                       val user : User? = snapshot.getValue(User::class.java)
                       Log.d("Data",user?.name.toString())
                       mutable.postValue(user)
                   }
                    }

                    override fun onCancelled(error: DatabaseError) {
                        Log.d("Error","Error From Firebase")
                    }

                })
        return mutable
    }
}
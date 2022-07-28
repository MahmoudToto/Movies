package com.example.movies.Register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movies.Pojo.User

class RegisterViewModel : ViewModel() {
    val repo = RegisterationRepo()
    private val _mutable = MutableLiveData<Boolean>()
    val mutable: LiveData<Boolean> = _mutable

    fun Registeration(user: User) {
        _mutable.postValue(repo.createUserWithEmailAndPassword(user))
    }

    //getApplication<Application>()?.startActivity(Intent(getApplication(),LoginActivity::class.java))


}
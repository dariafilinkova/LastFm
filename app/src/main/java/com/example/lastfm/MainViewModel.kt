package com.example.lastfm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.lastfm.login.Prefs

class MainViewModel : ViewModel() {

    val isUserAlreadyAuthorized = MutableLiveData<Boolean>()

    fun checkIfUserIsLoggedIn(prefs: Prefs) {
        isUserAlreadyAuthorized.value =
            prefs.password.isNotEmpty() && prefs.username.isNotEmpty()
    }
}
package com.example.lastfm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {

    val isUserAlreadyAuthorized = MutableLiveData<Boolean>()

    fun checkIfUserIsLoggedIn(prefs: Prefs) {
        isUserAlreadyAuthorized.value =
            prefs.password.isNotEmpty() && prefs.username.isNotEmpty()
    }
}
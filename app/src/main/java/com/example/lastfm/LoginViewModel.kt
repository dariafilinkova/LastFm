package com.example.lastfm

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lastfm.netwoking.retrofit.LastFmAPIProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.security.MessageDigest

class LoginViewModel : ViewModel() {

    val api = LastFmAPIProvider.api
    val authenticationIsDone = MutableLiveData<Boolean>()

    val wrongUserNameORPassword = MutableLiveData<String>()

    fun onSignInClick(username: String, password: String) {
        //if(!valid()){
        if (username.isEmpty() && password.isEmpty()) {
            wrongUserNameORPassword.postValue("Valid username or password")
            return
        }
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val apiSignature = getApiSignature(username, password)
                api.signIn(METHOD, APIKEY, username, password, apiSignature)
                authenticationIsDone.postValue(true)

            } catch (e: Exception) {
                Log.e("TAG", e.message.orEmpty())
                wrongUserNameORPassword.postValue(e.message)
            }
        }
    }

    private fun getApiSignature(username: String, password: String): String {
        val apiSignature =
            "api_key" + APIKEY + "methodauth.getMobileSessionpassword" + password +
                    "username" + username + APISIG
        val hexString = StringBuilder()
        val digest: MessageDigest = MessageDigest.getInstance("MD5")
        digest.update(apiSignature.toByteArray(charset("UTF-8")))
        val messageDigest: ByteArray = digest.digest()
        for (aMessageDigest in messageDigest) {
            var h = Integer.toHexString(0xFF and aMessageDigest.toInt())
            while (h.length < 2) h = "0$h"
            hexString.append(h)
        }

        return hexString.toString()
    }

    companion object {
        const val APIKEY = "62d2ee488c6a663e4ce355d285d0b234"
        const val APISIG = "7d086e6c1c172ecbf655af7f21b9f539"
        const val METHOD = "auth.getMobileSession"
    }
}
//       userName == "daria28122001" && password == "DDDfff123!"
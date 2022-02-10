package com.example.lastfm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import java.security.MessageDigest

class LoginViewModel : ViewModel() {
    private val scope = CoroutineScope(Dispatchers.Main)
    private val okHttpClient = OkHttpClient.Builder().build()

    private val authenticationIsDone = MutableLiveData<Boolean>()

    fun check(userName: String, password: String): Boolean {
        return userName == "daria28122001" && password == "DDDfff123!"
    }

    fun onSignInClick(login: String, password: String) {
        scope.launch {
            try {
                if (check(login, password)) {
                    val apiSignature =
                        "api_key" + APIKEY + "methodauth.getMobileSessionpassword" + password +
                                "username" + login + APISIG
                    val hexString = StringBuilder()
                    val digest: MessageDigest = MessageDigest.getInstance("MD5")
                    digest.update(apiSignature.toByteArray(charset("UTF-8")))
                    val messageDigest: ByteArray = digest.digest()
                    for (aMessageDigest in messageDigest) {
                        var h = Integer.toHexString(0xFF and aMessageDigest.toInt())
                        while (h.length < 2) h = "0$h"
                        hexString.append(h)
                    }
                    val urlParameter =
                        "method=auth.getMobileSession&api_key=" + APIKEY + "&password=" + password +
                                "&username=" + login + "&api_sig=" + hexString
                    val urlAdress = "https://ws.audioscrobbler.com/2.0/?$urlParameter"

                    withContext(Dispatchers.Main) {
                        val response = okHttpClient
                            .newCall(
                                Request.Builder()
                                    .url(urlAdress)
                                    .post(RequestBody.create(null, ByteArray(0)))
                                    .build()
                            ).execute()
                        authenticationIsDone.value = response.body.toString().contains("ok")
                    }
                }
            } catch (e: Exception) {
                authenticationIsDone.value = false
            }
        }
    }

    companion object {
        val APIKEY: String = "62d2ee488c6a663e4ce355d285d0b234"
        val APISIG: String = "7d086e6c1c172ecbf655af7f21b9f539"
    }
}
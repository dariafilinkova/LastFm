package com.example.lastfm

import android.content.Context
import android.content.SharedPreferences

class CustomPreference(
    private val context: Context
) : Prefs {

    private val sharedPreferences: SharedPreferences by lazy {
        context.getSharedPreferences(context.packageName + "_preferences", Context.MODE_PRIVATE)
    }

    override var username: String = ""
        get() = sharedPreferences.getString(USERNAME, "").orEmpty()
        set(value) {
            field = value
            sharedPreferences.edit().putString(USERNAME, value).apply()
        }

    override var password: String = ""
        get() = sharedPreferences.getString(PASSWORD, "").orEmpty()
        set(value) {
            field = value
            sharedPreferences.edit().putString(PASSWORD, value).apply()
        }

    companion object {
        private const val USERNAME = "username"
        private const val PASSWORD = "password"
    }
}
package com.example.lastfm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.lastfm.login.CustomPreference
import com.example.lastfm.login.LoginFragment

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()
    private val preferences by lazy {
        CustomPreference(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel.checkIfUserIsLoggedIn(preferences)
        viewModel.isUserAlreadyAuthorized.observe(this) { isUserAlreadyAuthorized ->
            val baseFragment = if (isUserAlreadyAuthorized) {
                ChartListFragment.newInstance()
            } else {
                LoginFragment.newInstance()
            }
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragmentContainer, baseFragment)
                .commit()
        }
    }
}
package com.example.lastfm.netwoking.retrofit

import com.example.lastfm.netwoking.api.LastFmAPI
import retrofit2.converter.gson.GsonConverterFactory

import retrofit2.Retrofit


object LastFmAPIProvider {
    private const val BASE_URL = "https://ws.audioscrobbler.com/2.0/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val api = retrofit.create(LastFmAPI::class.java)
}
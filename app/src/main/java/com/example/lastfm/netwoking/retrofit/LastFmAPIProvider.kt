package com.example.lastfm.netwoking.retrofit

import com.example.lastfm.netwoking.api.LastFmAPI
import okhttp3.OkHttpClient
import retrofit2.converter.gson.GsonConverterFactory

import retrofit2.Retrofit
import retrofit2.create


object LastFmAPIProvider {
     private const val BASE_URL = "https://ws.audioscrobbler.com/2.0/"

    private val client = OkHttpClient.Builder()
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val api = retrofit.create(LastFmAPI::class.java)
}
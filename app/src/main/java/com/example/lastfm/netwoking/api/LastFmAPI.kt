package com.example.lastfm.netwoking.api

import retrofit2.http.POST
import retrofit2.http.Query

interface LastFmAPI {

    @POST(".")
    suspend fun signIn(
        @Query("method") method: String,
        @Query("api_key") apiKey: String,
        @Query("username") username: String,
        @Query("password") password: String,
        @Query("api_sig") apiSignature: String,

        )
}
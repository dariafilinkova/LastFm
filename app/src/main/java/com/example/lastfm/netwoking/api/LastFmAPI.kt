package com.example.lastfm.netwoking.api

import com.example.lastfm.data.TrackDTO
import retrofit2.http.GET
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

    @GET(".")
    suspend fun loadTrack(
        @Query("method") method: String,
        @Query("api_key") apiKey: String ,
        @Query("format") format: String,
    ) :TrackDTO
}
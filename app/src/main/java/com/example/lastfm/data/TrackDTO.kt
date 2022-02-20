package com.example.lastfm.data
import com.google.gson.annotations.SerializedName


data class TrackDTO(
    @SerializedName("tracks")
    val tracks: Tracks
)

data class Tracks(
    @SerializedName("track")
    val track: List<TopTrack>?
)
data class TopTrack(
    @SerializedName("artist")
    val artist: Artist,
    @SerializedName("image")
    val image: List<Image>,
    @SerializedName("name")
    val name: String
)
data class Artist(
    @SerializedName("name")
    val name: String,
)

data class Image(
    @SerializedName("size")
    val size: String,
    @SerializedName("#text")
    val text: String
)
package com.example.lastfm.chartList

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.lastfm.data.Track
import com.example.lastfm.netwoking.retrofit.LastFmAPIProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ChartListViewModel : ViewModel() {

    private val scope = CoroutineScope(Dispatchers.Main)
    val trackLiveData = MutableLiveData<List<Track>>()
    private val authenticationIsDone = MutableLiveData<Boolean>()
    val api = LastFmAPIProvider.api

    fun showListOfTracks() {
        scope.launch {
            try {
                val chart = withContext(Dispatchers.IO) {
                    getMedia()
                }
                authenticationIsDone.value = true
                trackLiveData.value = chart
            } catch (e: Exception) {
                if (trackLiveData.value.isNullOrEmpty()) {
                    authenticationIsDone.value = false
                }
            }
        }
    }

    suspend fun getMedia(): List<Track> {
        return api.loadTrack(METHOD, API_KEY, RESPONSE_FORMAT_JSON)
            .tracks.track?.map {
                Track(
                    artist = it.artist.name,
                    image = it.image[1].text,
                    name = it.name,
                )
            }.orEmpty()
    }

    companion object {
        private const val METHOD = "chart.gettoptracks"
        private const val API_KEY = "62d2ee488c6a663e4ce355d285d0b234"
        private const val RESPONSE_FORMAT_JSON = "json"
    }
}
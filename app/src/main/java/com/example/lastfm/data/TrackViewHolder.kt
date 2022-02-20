package com.example.lastfm.data

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.lastfm.R

class TrackViewHolder(view:View):RecyclerView.ViewHolder(view){
    val trackName = view.findViewById<TextView>(R.id.songName)
    val artist = view.findViewById<TextView>(R.id.artist)
    val image = view.findViewById<ImageView>(R.id.image)


    fun input(track: Track) {
        trackName.text = track.name
        artist.text = track.artist
        Glide.with(image.context)
            .load(track.image)
            .into(image)
    }
}
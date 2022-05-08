package com.example.groovy

import com.example.groovy.model.ModelPlaylistItem
import retrofit2.http.GET

interface PlaylistApi {
    @GET("playlists/")
    suspend fun fetchAllPlaylist() :List<ModelPlaylistItem>
}

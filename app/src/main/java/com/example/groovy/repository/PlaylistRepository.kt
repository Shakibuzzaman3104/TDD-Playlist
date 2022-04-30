package com.example.groovy.repository

import com.example.groovy.PlaylistService
import com.example.groovy.model.ModelPlaylistItem
import kotlinx.coroutines.flow.Flow


class PlaylistRepository(private val playlistService: PlaylistService) {
    suspend fun getPlaylists(): Flow<Result<List<ModelPlaylistItem>>> =
        playlistService.fetchPlaylist()

}
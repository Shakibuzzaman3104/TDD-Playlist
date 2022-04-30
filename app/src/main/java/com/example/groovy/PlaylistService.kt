package com.example.groovy

import com.example.groovy.model.ModelPlaylistItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class PlaylistService(
    private val api: PlaylistApi
) {
    fun fetchPlaylist(): Flow<Result<List<ModelPlaylistItem>>> {
        return flow { emit(Result.success(api.fetchAllPlaylist())) }
    }
}
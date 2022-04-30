package com.example.groovy.repository

import com.example.groovy.model.ModelPlaylistItem
import kotlinx.coroutines.flow.Flow


class PlaylistRepository {
    suspend fun getPlaylists() : Flow<Result<List<ModelPlaylistItem>>> {
        TODO("asfdasf")
    }
}
package com.example.groovy.viewmodel

import androidx.lifecycle.*
import com.example.groovy.repository.PlaylistRepository
import com.example.groovy.model.ModelPlaylistItem
import kotlinx.coroutines.launch

class PlaylistViewModel(private val repo: PlaylistRepository) : ViewModel() {

    val playlists = liveData {
        emitSource(repo.getPlaylists().asLiveData())
    }

}
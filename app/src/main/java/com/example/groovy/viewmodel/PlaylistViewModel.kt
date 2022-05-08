package com.example.groovy.viewmodel

import androidx.lifecycle.*
import com.example.groovy.repository.PlaylistRepository
import com.example.groovy.model.ModelPlaylistItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlaylistViewModel @Inject constructor(val repo: PlaylistRepository) : ViewModel() {

    val playlists = liveData {
        emitSource(repo.getPlaylists().asLiveData())
    }

}
package com.example.groovy.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.groovy.repository.PlaylistRepository

class PlaylistViewModelFactory(
    private val repo: PlaylistRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PlaylistViewModel(repo) as T
    }
}
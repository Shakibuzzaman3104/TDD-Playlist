package com.example.groovy.playlist

import com.example.groovy.PlaylistService
import com.example.groovy.model.ModelPlaylistItem
import com.example.groovy.repository.PlaylistRepository
import com.example.groovy.utils.BaseUnitTest
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito.*
import java.lang.RuntimeException

class PlaylistRepositoryShould : BaseUnitTest() {

    private val service: PlaylistService = mock()
    private val playlists = mock<List<ModelPlaylistItem>>()
    private val exception = RuntimeException("Something went wrong")

    @Test
    fun getPlaylistFromService() = runTest {
        val repository = PlaylistRepository(service)
        repository.getPlaylists()
        verify(service, times(1)).fetchPlaylist()
    }

    @Test
    fun emitPlaylistFromService() = runTest {
        val repo = mockSuccessfulCase()
        assertEquals(playlists, repo.getPlaylists().first().getOrNull())
    }

    @Test
    fun propagateErrors() = runTest{
        whenever(service.fetchPlaylist()).thenReturn(
            flow { emit(Result.failure(exception)) }
        )

        val repo = PlaylistRepository(service)
        assertEquals(exception, repo.getPlaylists().first().exceptionOrNull())

    }

    private fun mockSuccessfulCase(): PlaylistRepository {
        whenever(service.fetchPlaylist()).thenReturn(
            flow { emit(Result.success(playlists)) }
        )
        return PlaylistRepository(service)
    }

}
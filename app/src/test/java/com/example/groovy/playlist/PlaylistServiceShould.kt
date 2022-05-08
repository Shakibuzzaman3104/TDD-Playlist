package com.example.groovy.playlist

import com.example.groovy.PlaylistApi
import com.example.groovy.PlaylistService
import com.example.groovy.model.ModelPlaylistItem
import com.example.groovy.utils.BaseUnitTest
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class PlaylistServiceShould : BaseUnitTest() {

    private lateinit var service: PlaylistService
    private val api: PlaylistApi = mock()
    private val playlists: List<ModelPlaylistItem> = mock()

    @Test
    fun fetchPlaylistFromApi() = runTest {
        service = PlaylistService(api)
        service.fetchPlaylist().first() // .first is called to force emission
        verify(api, times(1)).fetchAllPlaylist()
    }

    @Test
    fun convertValuesToFlowAndEmitThem() = runTest{
        mockSuccessfulCase()
        assertEquals(Result.success(playlists), service.fetchPlaylist().first())
    }

    @Test
    fun mockSuccessfulCase() = runTest{
        whenever(api.fetchAllPlaylist()).thenReturn(playlists)
        service = PlaylistService(api)
    }

    @Test
     fun emitErrorResultWhenNetworkFails() = runTest {
        whenever(api.fetchAllPlaylist()).thenThrow(RuntimeException("Backend issues"))
        val service = PlaylistService(api)

        assertEquals("Something went wrong", service.fetchPlaylist().first().exceptionOrNull()?.message)

    }


}
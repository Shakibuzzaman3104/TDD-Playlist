package com.example.groovy.playlist

import com.example.groovy.model.ModelPlaylistItem
import com.example.groovy.repository.PlaylistRepository
import com.example.groovy.viewmodel.PlaylistViewModel
import com.example.groovy.utils.BaseUnitTest
import com.example.groovy.utils.getValueForTest
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import java.lang.RuntimeException


class PlaylistViewModelShould : BaseUnitTest() {

    private lateinit var viewModel: PlaylistViewModel
    private val playlistRepository: PlaylistRepository = mock()
    private val playlists = mock<List<ModelPlaylistItem>>()
    private val expected = Result.success(playlists)
    private val exception = RuntimeException("Something went wrong")

    @Test
    fun getPlaylistFromRepository() = runTest {
        viewModel = mockSuccessfulCase()
        viewModel.playlists.getValueForTest()
        verify(playlistRepository, times(1)).getPlaylists()
    }

    @Test
    fun emitPlaylistFromRepository() = runTest {
        viewModel = mockSuccessfulCase()
        viewModel = PlaylistViewModel(playlistRepository)
        assertEquals(expected, viewModel.playlists.getValueForTest())
    }

    @Test
    fun emitErrorWhenReceiveError() {
        runTest {
            whenever(playlistRepository.getPlaylists()).thenReturn(
                flow {
                    emit(Result.failure(exception))
                }
            )
        }
        val viewModel = PlaylistViewModel(playlistRepository)
        assertEquals(exception, viewModel.playlists.getValueForTest()!!.exceptionOrNull())

    }


    private fun mockSuccessfulCase(): PlaylistViewModel {
        runTest {
            whenever(playlistRepository.getPlaylists()).thenReturn(
                flow { emit(expected) }
            )
        }

        return PlaylistViewModel(playlistRepository)
    }
}
package com.example.groovy

import android.util.Log
import com.example.groovy.model.ModelPlaylistItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import java.lang.RuntimeException
import javax.inject.Inject

class PlaylistService @Inject constructor(
    private val api: PlaylistApi
) {
    fun fetchPlaylist(): Flow<Result<List<ModelPlaylistItem>>> {
        return flow {
            emit(Result.success(api.fetchAllPlaylist()))
        }.catch {
            this.runCatching {     Log.d("FetchPlayList", ""+it.localizedMessage) }
            emit(Result.failure(RuntimeException("Something went wrong")))
        }
    }
}
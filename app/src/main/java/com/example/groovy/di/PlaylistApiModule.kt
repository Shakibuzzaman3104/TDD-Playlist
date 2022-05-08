package com.example.groovy.di

import com.example.groovy.PlaylistApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@InstallIn(SingletonComponent::class)
@Module
class PlaylistApiModule {

    @Provides
    fun provideRetrofit(): Retrofit {
       return Retrofit.Builder()
            .baseUrl("http://192.168.0.161:3000/")
            .client(OkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun providesPlaylistApi(retrofit:Retrofit): PlaylistApi = retrofit.create(PlaylistApi::class.java)

}
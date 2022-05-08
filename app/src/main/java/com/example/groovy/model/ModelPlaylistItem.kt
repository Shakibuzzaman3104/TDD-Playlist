package com.example.groovy.model


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep


@Keep
data class ModelPlaylistItem(
    @SerializedName("category")
    val category: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String
)
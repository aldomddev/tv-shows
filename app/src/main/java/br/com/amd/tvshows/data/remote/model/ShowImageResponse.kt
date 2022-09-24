package br.com.amd.tvshows.data.remote.model

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class ShowImageResponse(
    @SerialName("medium")
    val medium: String,

    @SerialName("original")
    val original: String
)

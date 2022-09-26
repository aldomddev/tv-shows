package br.com.amd.tvshows.data.remote.model

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class SearchShowResponse(
    @SerialName("show")
    val show: ShowResponse
)

package br.com.amd.tvshows.data.remote.model

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class ShowRatingResponse(
    @SerialName("average")
    val average: Float = 0.0f
)
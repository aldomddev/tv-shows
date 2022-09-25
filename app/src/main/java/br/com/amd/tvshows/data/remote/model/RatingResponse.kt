package br.com.amd.tvshows.data.remote.model

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class RatingResponse(
    @SerialName("average")
    val average: Double? = null
)

package br.com.amd.tvshows.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ShowResponse(

    @SerialName("id")
    val id: Long,

    @SerialName("name")
    val name: String,

    @SerialName("rating")
    val rating: ShowRatingResponse? = null,

    @SerialName("image")
    val imageResponse: ShowImageResponse? = null
)
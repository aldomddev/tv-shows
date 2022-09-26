package br.com.amd.tvshows.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ShowEpisodeResponse(

    @SerialName("id")
    val id: Long,

    @SerialName("name")
    val name: String,

    @SerialName("summary")
    val summary: String? = "",

    @SerialName("season")
    val season: Int,

    @SerialName("number")
    val number: Int,

    @SerialName("image")
    val imageResponse: ImageResponse? = null
)

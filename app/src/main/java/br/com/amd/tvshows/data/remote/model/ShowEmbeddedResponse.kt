package br.com.amd.tvshows.data.remote.model

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class ShowEmbeddedResponse(
    @SerialName("episodes")
    val episodes: List<ShowEpisodeResponse>? = null
)

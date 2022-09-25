package br.com.amd.tvshows.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ShowResponse(

    @SerialName("id")
    val id: Long,

    @SerialName("name")
    val name: String,

    @SerialName("summary")
    val summary: String,

    @SerialName("schedule")
    val schedule: ShowScheduleResponse? = null,

    @SerialName("genres")
    val genres: List<String>? = null,

    @SerialName("rating")
    val rating: RatingResponse? = null,

    @SerialName("image")
    val imageResponse: ImageResponse? = null,

    @SerialName("episodes")
    val episodes: List<ShowEpisodeResponse>? = null
)
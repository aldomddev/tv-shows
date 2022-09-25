package br.com.amd.tvshows.data.remote.model

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class ShowScheduleResponse(
    @SerialName("time")
    val time: String,

    @SerialName("days")
    val days: List<String>
)

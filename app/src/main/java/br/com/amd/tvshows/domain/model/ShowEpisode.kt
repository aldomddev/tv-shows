package br.com.amd.tvshows.domain.model

data class ShowEpisode(
    val id: Long,
    val name: String,
    val summary: String,
    val season: Int,
    val number: Int,
    val mediumImageUrl: String,
    val originalImageUrl: String,
)

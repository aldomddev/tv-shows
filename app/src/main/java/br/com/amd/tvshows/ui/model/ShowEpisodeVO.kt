package br.com.amd.tvshows.ui.model

data class ShowEpisodeVO(
    val id: Long,
    val name: String,
    val summary: String,
    val season: Int,
    val number: Int,
    val mediumImageUrl: String,
    val originalImageUrl: String,
)

package br.com.amd.tvshows.domain.model

data class Show(
    val id: Long,
    val name: String,
    val summary: String,
    val rating: Double,
    val mediumImageUrl: String,
    val originalImageUrl: String,
    val genres: List<String>,
    val schedule: ShowSchedule,
    val episodes: List<ShowEpisode>
)
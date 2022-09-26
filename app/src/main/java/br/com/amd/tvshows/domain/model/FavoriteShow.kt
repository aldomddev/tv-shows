package br.com.amd.tvshows.domain.model

data class FavoriteShow(
    val id: Long,
    val showId: Long,
    val name: String,
    val imageUrl: String,
)
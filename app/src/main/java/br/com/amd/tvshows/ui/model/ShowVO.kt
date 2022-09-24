package br.com.amd.tvshows.ui.model

data class ShowVO(
    val id: Long,
    val name: String,
    val rating: Double,
    val mediumImageUrl: String,
    val originalImageUrl: String
)
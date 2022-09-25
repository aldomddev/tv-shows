package br.com.amd.tvshows.domain.model

data class ShowSchedule(
    val time: String,
    val days: List<String>
)

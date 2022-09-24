package br.com.amd.tvshows.data.remote.mapper

import br.com.amd.tvshows.data.remote.model.ShowResponse
import br.com.amd.tvshows.domain.model.Show

fun ShowResponse.toDomainShow(): Show {
    return Show(
        id = id,
        name = name,
        rating = rating?.average ?: 0.0,
        mediumImageUrl = imageResponse?.medium.orEmpty(),
        originalImageUrl = imageResponse?.original.orEmpty()
    )
}

fun List<ShowResponse>.toDomainShow() = this.map { it.toDomainShow() }
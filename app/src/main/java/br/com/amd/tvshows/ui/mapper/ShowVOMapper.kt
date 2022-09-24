package br.com.amd.tvshows.ui.mapper

import br.com.amd.tvshows.domain.model.Show
import br.com.amd.tvshows.ui.model.ShowVO

fun Show.toShowPresenter() : ShowVO {
    return ShowVO(
        id = id,
        name = name,
        rating = rating,
        mediumImageUrl = mediumImageUrl,
        originalImageUrl = originalImageUrl
    )
}

fun List<Show>.toShowPresenter() = this.map { it.toShowPresenter() }
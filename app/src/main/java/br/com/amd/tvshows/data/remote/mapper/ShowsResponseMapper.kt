package br.com.amd.tvshows.data.remote.mapper

import br.com.amd.tvshows.data.remote.model.ShowEpisodeResponse
import br.com.amd.tvshows.data.remote.model.ShowResponse
import br.com.amd.tvshows.data.remote.model.ShowScheduleResponse
import br.com.amd.tvshows.domain.model.Show
import br.com.amd.tvshows.domain.model.ShowEpisode
import br.com.amd.tvshows.domain.model.ShowSchedule

fun ShowResponse.toDomainShow(): Show {
    return Show(
        id = id,
        name = name,
        summary = summary,
        rating = rating?.average ?: 0.0,
        mediumImageUrl = imageResponse?.medium.orEmpty(),
        originalImageUrl = imageResponse?.original.orEmpty(),
        genres = genres.orEmpty(),
        schedule = schedule?.toShowScheduleDomain() ?: ShowSchedule(time = "", days = emptyList()),
        episodes = embedded?.episodes?.toShowEpisodeDomain() ?: emptyList()
    )
}

fun List<ShowResponse>.toDomainShow() = map { it.toDomainShow() }

fun ShowScheduleResponse.toShowScheduleDomain(): ShowSchedule {
    return ShowSchedule(
        time = time,
        days = days
    )
}

fun ShowEpisodeResponse.toShowEpisodeDomain(): ShowEpisode {
    return ShowEpisode(
        id,
        name,
        summary,
        season,
        number,
        mediumImageUrl = imageResponse?.medium.orEmpty(),
        originalImageUrl = imageResponse?.original.orEmpty(),
    )
}

fun List<ShowEpisodeResponse>.toShowEpisodeDomain() = map { it.toShowEpisodeDomain() }
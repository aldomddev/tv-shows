package br.com.amd.tvshows.ui.mapper

import br.com.amd.tvshows.domain.model.Show
import br.com.amd.tvshows.domain.model.ShowEpisode
import br.com.amd.tvshows.domain.model.ShowSchedule
import br.com.amd.tvshows.ui.model.ShowEpisodeVO
import br.com.amd.tvshows.ui.model.ShowScheduleVO
import br.com.amd.tvshows.ui.model.ShowVO

fun Show.toShowUi(): ShowVO {
    return ShowVO(
        id = id,
        name = name,
        summary = summary,
        rating = rating,
        mediumImageUrl = mediumImageUrl,
        originalImageUrl = originalImageUrl,
        genres = genres,
        schedule = schedule.toShowScheduleUi(),
        episodes = episodes.toShowEpisodeUi()
    )
}

fun List<Show>.toShowUi() = map { it.toShowUi() }

fun ShowSchedule.toShowScheduleUi(): ShowScheduleVO {
    return ShowScheduleVO(time, days)
}

fun ShowEpisode.toShowEpisodeUi(): ShowEpisodeVO {
    return ShowEpisodeVO(
        id = id,
        name = name,
        summary = summary,
        season = season,
        number = number,
        mediumImageUrl = mediumImageUrl,
        originalImageUrl = originalImageUrl
    )
}

fun List<ShowEpisode>.toShowEpisodeUi() = map { it.toShowEpisodeUi() }


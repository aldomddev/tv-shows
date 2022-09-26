package br.com.amd.tvshows.ui.mapper

import br.com.amd.tvshows.domain.model.FavoriteShow
import br.com.amd.tvshows.domain.model.Show
import br.com.amd.tvshows.domain.model.ShowEpisode
import br.com.amd.tvshows.domain.model.ShowSchedule
import br.com.amd.tvshows.ui.model.ShowEpisodeVO
import br.com.amd.tvshows.ui.model.ShowScheduleVO
import br.com.amd.tvshows.ui.model.ShowSeasonVO
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
        seasons = episodes.toShowSeasonsUi()
    )
}

fun List<Show>.toShowUi() = map { it.toShowUi() }

fun ShowVO.toFavoriteShowDomain(): FavoriteShow {
    return FavoriteShow(
        id = favoriteId,
        showId = id,
        name = name,
        imageUrl = mediumImageUrl
    )
}

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

fun List<ShowEpisode>.toShowSeasonsUi(): List<ShowSeasonVO> {
    val seasonsAndEpisodesMap = this.groupBy { showEpisode -> showEpisode.season}

    val seasons = mutableListOf<ShowSeasonVO>()
    seasonsAndEpisodesMap.forEach { s ->
        val season = ShowSeasonVO(
            number = s.key,
            episodes = s.value.toShowEpisodeUi()
        )
        seasons.add(season)
    }

    return seasons
}

fun FavoriteShow.toShowUi(): ShowVO {
    return ShowVO(
        id = showId,
        favoriteId = id,
        name = name,
        summary = "",
        rating = 0.0,
        mediumImageUrl = imageUrl,
        originalImageUrl = "",
        genres = emptyList(),
        schedule = ShowScheduleVO(time = "", days = emptyList()),
        seasons = emptyList()
    )
}

fun List<FavoriteShow>.toShowVOUi() = map { it.toShowUi() }


package br.com.amd.tvshows.domain.repository

import br.com.amd.tvshows.domain.model.FavoriteShow
import br.com.amd.tvshows.domain.model.Show

interface ShowsRepository {
    suspend fun getShowDetailsById(showId: Long): Show
    suspend fun searchShows(showName: String): List<Show>
    suspend fun getAllFavoriteShows(): List<FavoriteShow>
    suspend fun findFavoriteByShowId(showId: Long): FavoriteShow?
    suspend fun saveFavoriteShow(show: FavoriteShow)
    suspend fun deleteFavoriteShow(show: FavoriteShow)
}
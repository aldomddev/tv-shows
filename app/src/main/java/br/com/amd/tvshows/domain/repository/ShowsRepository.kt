package br.com.amd.tvshows.domain.repository

import br.com.amd.tvshows.domain.model.FavoriteShow
import br.com.amd.tvshows.domain.model.Show
import kotlinx.coroutines.flow.Flow

interface ShowsRepository {
    suspend fun getShowDetailsById(showId: Long): Show
    suspend fun searchShows(showName: String): List<Show>
    suspend fun getFavouriteShows(): Flow<List<FavoriteShow>>
    suspend fun findByShowId(showId: Long): FavoriteShow?
    suspend fun saveOrUpdate(show: FavoriteShow)
    suspend fun delete(show: FavoriteShow)
}
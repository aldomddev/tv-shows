package br.com.amd.tvshows.data.repository

import br.com.amd.tvshows.data.local.dao.FavoriteShowsDao
import br.com.amd.tvshows.data.local.mapper.toFavouriteShowData
import br.com.amd.tvshows.data.local.mapper.toFavouriteShowDomain
import br.com.amd.tvshows.data.remote.api.TvMazeApi
import br.com.amd.tvshows.data.remote.mapper.toDomainShow
import br.com.amd.tvshows.di.IoDispatcher
import br.com.amd.tvshows.domain.model.FavoriteShow
import br.com.amd.tvshows.domain.model.Show
import br.com.amd.tvshows.domain.repository.ShowsRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ShowsRepositoryImpl @Inject constructor(
    private val tvMazeApi: TvMazeApi,
    private val favoriteShowsDao: FavoriteShowsDao,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : ShowsRepository {


    override suspend fun getShowDetailsById(showId: Long): Show {
        return withContext(ioDispatcher) {
            tvMazeApi.getShowDetailsById(showId).toDomainShow()
        }
    }

    override suspend fun searchShows(showName: String): List<Show> {
        return withContext(ioDispatcher) {
            val result = tvMazeApi.searchShows(showName).map {
                it.show.toDomainShow()
            }
            result
        }
    }

    override suspend fun getAllFavoriteShows(): List<FavoriteShow> {
        return withContext(ioDispatcher) {
            favoriteShowsDao.getAll().toFavouriteShowDomain()
        }
    }

    override suspend fun findFavoriteByShowId(showId: Long): FavoriteShow? {
        return withContext(ioDispatcher) {
            favoriteShowsDao.findByShowId(showId)?.toFavouriteShowDomain()
        }
    }

    override suspend fun saveFavoriteShow(show: FavoriteShow) {
        return withContext(ioDispatcher) {
            favoriteShowsDao.saveOrUpdate(show.toFavouriteShowData())
        }
    }

    override suspend fun deleteFavoriteShow(show: FavoriteShow) {
        return withContext(ioDispatcher) {
            favoriteShowsDao.delete(show.toFavouriteShowData())
        }
    }
}
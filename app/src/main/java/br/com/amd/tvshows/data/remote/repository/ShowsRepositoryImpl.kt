package br.com.amd.tvshows.data.remote.repository

import br.com.amd.tvshows.data.local.dao.ShowsDao
import br.com.amd.tvshows.data.local.mapper.toFavouriteShowData
import br.com.amd.tvshows.data.local.mapper.toFavouriteShowDomain
import br.com.amd.tvshows.data.remote.api.TvMazeApi
import br.com.amd.tvshows.data.remote.mapper.toDomainShow
import br.com.amd.tvshows.di.IoDispatcher
import br.com.amd.tvshows.domain.model.FavoriteShow
import br.com.amd.tvshows.domain.model.Show
import br.com.amd.tvshows.domain.repository.ShowsRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.transform
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ShowsRepositoryImpl @Inject constructor(
    private val tvMazeApi: TvMazeApi,
    private val showsDao: ShowsDao,
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

    override suspend fun getFavouriteShows(): Flow<List<FavoriteShow>> {
        return withContext(ioDispatcher) {
            showsDao.all().transform { show -> emit(show.toFavouriteShowDomain()) }
        }
    }

    override suspend fun findByShowId(showId: Long): FavoriteShow? {
        return withContext(ioDispatcher) {
            showsDao.findByShowId(showId)?.toFavouriteShowDomain()
        }
    }

    override suspend fun saveOrUpdate(show: FavoriteShow) {
        return withContext(ioDispatcher) {
            showsDao.saveOrUpdate(show.toFavouriteShowData())
        }
    }

    override suspend fun delete(show: FavoriteShow) {
        return withContext(ioDispatcher) {
            showsDao.delete(show.toFavouriteShowData())
        }
    }
}
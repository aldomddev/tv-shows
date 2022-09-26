package br.com.amd.tvshows.data.remote

import br.com.amd.tvshows.data.remote.api.TvMazeApi
import br.com.amd.tvshows.data.remote.mapper.toDomainShow
import br.com.amd.tvshows.di.IoDispatcher
import br.com.amd.tvshows.domain.model.Show
import br.com.amd.tvshows.domain.repository.ShowsRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ShowsRepositoryImpl @Inject constructor(
    private val tvMazeApi: TvMazeApi,
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
}
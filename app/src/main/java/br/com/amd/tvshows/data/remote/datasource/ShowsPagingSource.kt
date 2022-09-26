package br.com.amd.tvshows.data.remote.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import br.com.amd.tvshows.data.remote.api.TvMazeApi
import br.com.amd.tvshows.data.remote.mapper.toDomainShow
import br.com.amd.tvshows.domain.model.Show
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class ShowsPagingSource @Inject constructor(
    private val tvMazeApi: TvMazeApi
) : PagingSource<Int, Show>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Show> {
        val pageIndex = params.key ?: STARTING_PAGE

        return try {
            val showsResponse = tvMazeApi.getAllShows(page = pageIndex)

            val prevPage = if (pageIndex == STARTING_PAGE) null else pageIndex - 1
            val nextPage = if (showsResponse.isEmpty()) null else pageIndex + 1

            LoadResult.Page(
                data = showsResponse.toDomainShow(),
                prevKey = prevPage,
                nextKey = nextPage
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Show>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    companion object {
        const val STARTING_PAGE = 0
        const val ITEMS_PER_PAGE = 250
    }
}
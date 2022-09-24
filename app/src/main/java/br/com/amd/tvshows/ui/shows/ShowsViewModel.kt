package br.com.amd.tvshows.ui.shows

import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import br.com.amd.tvshows.data.remote.datasource.ShowsPagingSource
import br.com.amd.tvshows.ui.mapper.toShowPresenter
import br.com.amd.tvshows.ui.model.ShowVO
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class ShowsViewModel @Inject constructor(
    private val showsPagingSource: ShowsPagingSource
) : ViewModel() {

    val shows: Flow<PagingData<ShowVO>> = Pager(
        config = PagingConfig(
            pageSize = ShowsPagingSource.ITEMS_PER_PAGE,
            enablePlaceholders = false
        ),
        pagingSourceFactory = { showsPagingSource }
    ).flow.map { pagingData ->
        pagingData.map { it.toShowPresenter() }
    }
}
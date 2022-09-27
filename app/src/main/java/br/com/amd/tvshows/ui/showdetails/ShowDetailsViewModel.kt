package br.com.amd.tvshows.ui.showdetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.amd.tvshows.domain.repository.ShowsRepository
import br.com.amd.tvshows.ui.common.ViewState
import br.com.amd.tvshows.ui.mapper.toFavoriteShowDomain
import br.com.amd.tvshows.ui.mapper.toShowUi
import br.com.amd.tvshows.ui.model.ShowVO
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShowDetailsViewModel @Inject constructor(
    private val showsRepository: ShowsRepository
) : ViewModel() {

    private val _viewState = MutableLiveData<ViewState<ShowVO>>()
    val viewState: LiveData<ViewState<ShowVO>> = _viewState

    fun fetchShowDetails(showId: Long) {
        _viewState.value = ViewState.Loading

        viewModelScope.launch {
            try {
                val showDetails = showsRepository.getShowDetailsById(showId)
                val favoriteId = showsRepository.findFavoriteByShowId(showId)?.showId ?: 0L
                _viewState.value =
                    ViewState.Loaded(data = showDetails.toShowUi().copy(favoriteId = favoriteId))
            } catch (error: Exception) {
                _viewState.value = ViewState.Error
            }
        }
    }

    fun onFavoriteStateChanged(show: ShowVO?, addedToFavoriteList: Boolean) {
        if (show == null) return

        viewModelScope.launch {
            try {
                when {
                    addedToFavoriteList -> showsRepository.saveFavoriteShow(show.toFavoriteShowDomain())
                    else -> showsRepository.deleteFavoriteShow(
                        show.toFavoriteShowDomain().copy(id = show.favoriteId)
                    )
                }
            } catch (error: Exception) {
                _viewState.value = ViewState.Error
            }
        }
    }
}
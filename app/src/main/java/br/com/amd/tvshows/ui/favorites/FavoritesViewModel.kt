package br.com.amd.tvshows.ui.favorites

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.amd.tvshows.domain.repository.ShowsRepository
import br.com.amd.tvshows.ui.common.ViewState
import br.com.amd.tvshows.ui.mapper.toFavoriteShowDomain
import br.com.amd.tvshows.ui.mapper.toShowVOUi
import br.com.amd.tvshows.ui.model.ShowVO
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val showsRepository: ShowsRepository
) : ViewModel() {

    private val _viewState = MutableLiveData<ViewState<List<ShowVO>>>()
    val viewState: LiveData<ViewState<List<ShowVO>>> = _viewState

    fun getFavoriteShows() {
        viewModelScope.launch {
            try {
                val favorites = showsRepository.getAllFavoriteShows()
                val viewState = if (favorites.isEmpty()) {
                    ViewState.Empty
                } else {
                    ViewState.Loaded(favorites.toShowVOUi())
                }
                _viewState.value = viewState
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
                    else -> showsRepository.deleteFavoriteShow(show.toFavoriteShowDomain())
                }

                getFavoriteShows()
            } catch (error: Exception) {
                _viewState.value = ViewState.Error
            }
        }
    }
}
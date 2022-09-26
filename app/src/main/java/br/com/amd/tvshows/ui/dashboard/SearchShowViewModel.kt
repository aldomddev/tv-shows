package br.com.amd.tvshows.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.amd.tvshows.domain.repository.ShowsRepository
import br.com.amd.tvshows.ui.mapper.toShowUi
import br.com.amd.tvshows.ui.model.ShowVO
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchShowViewModel @Inject constructor(
    private val showsRepository: ShowsRepository
) : ViewModel() {

    private val _searchViewState = MutableLiveData<SearchShowViewState>()
    val searchViewState: LiveData<SearchShowViewState> = _searchViewState

    fun search(showName: String) {
        _searchViewState.value = SearchShowViewState.Loading

        viewModelScope.launch {
            try {
                val result = showsRepository.searchShows(showName).toShowUi()

                if (result.isNotEmpty()) {
                    _searchViewState.value = SearchShowViewState.Loaded(result)
                } else {
                    _searchViewState.value = SearchShowViewState.Empty
                }
            } catch (error: Exception) {
                _searchViewState.value = SearchShowViewState.Error
            }
        }
    }

    sealed class SearchShowViewState {
        object Loading : SearchShowViewState()
        object Error : SearchShowViewState()
        object Empty : SearchShowViewState()
        data class Loaded(val data: List<ShowVO>) : SearchShowViewState()
    }
}
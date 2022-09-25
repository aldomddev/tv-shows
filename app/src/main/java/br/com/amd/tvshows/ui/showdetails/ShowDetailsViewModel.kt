package br.com.amd.tvshows.ui.showdetails

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
class ShowDetailsViewModel @Inject constructor(
    private val showsRepository: ShowsRepository
) : ViewModel() {

    private val _viewState = MutableLiveData<ShowDetailsViewState>()
    val viewState: LiveData<ShowDetailsViewState> = _viewState

    fun fetchShowDetails(showId: Long) {
        _viewState.value = ShowDetailsViewState.Loading

        viewModelScope.launch {
            try {
                val showDetails = showsRepository.getShowDetailsById(showId)
                _viewState.value = ShowDetailsViewState.Loaded(data = showDetails.toShowUi())
            } catch (error: Exception) {
                _viewState.value = ShowDetailsViewState.Error
            }
        }
    }

    sealed class ShowDetailsViewState {
        object Loading : ShowDetailsViewState()
        object Error : ShowDetailsViewState()
        data class Loaded(val data: ShowVO) : ShowDetailsViewState()
    }
}
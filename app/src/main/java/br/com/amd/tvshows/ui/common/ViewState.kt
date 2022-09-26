package br.com.amd.tvshows.ui.common

sealed class ViewState<out T> {
    object Loading : ViewState<Nothing>()
    object Error : ViewState<Nothing>()
    object Empty : ViewState<Nothing>()
    data class Loaded<T>(val data: T) : ViewState<T>()
}

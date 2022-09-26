package br.com.amd.tvshows.domain.repository

import br.com.amd.tvshows.domain.model.Show

interface ShowsRepository {
    suspend fun getShowDetailsById(showId: Long): Show
    suspend fun searchShows(showName: String): List<Show>
}
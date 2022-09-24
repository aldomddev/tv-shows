package br.com.amd.tvshows.data.remote.api

import br.com.amd.tvshows.data.remote.model.ShowResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface TvMazeApi {

    @GET("shows")
    suspend fun getAllShows(@Query("page") page: Int): List<ShowResponse>

    companion object {
        const val HOST = "https://api.tvmaze.com/"
    }
}
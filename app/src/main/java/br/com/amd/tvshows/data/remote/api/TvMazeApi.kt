package br.com.amd.tvshows.data.remote.api

import br.com.amd.tvshows.data.remote.model.SearchShowResponse
import br.com.amd.tvshows.data.remote.model.ShowResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TvMazeApi {

    @GET("shows")
    suspend fun getAllShows(@Query("page") page: Int): List<ShowResponse>

    @GET("shows/{id}?embed=episodes")
    suspend fun getShowDetailsById(@Path("id") showId: Long): ShowResponse

    @GET("search/shows")
    suspend fun searchShows(@Query("q") showName: String): List<SearchShowResponse>

    companion object {
        const val HOST = "https://api.tvmaze.com/"
    }
}
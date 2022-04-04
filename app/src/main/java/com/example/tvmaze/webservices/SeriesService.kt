package com.example.tvmaze.webservices

import com.example.tvmaze.network.model.*
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SeriesService {
    @GET("shows")
    suspend fun getSeriesList(
        @Query("page") page: Int
    ): List<SeriesDto>

    @GET("shows/{seriesId}")
    suspend fun getSeriesDetails(
        @Path("seriesId") seriesId: Int
    ): SeriesDetailsDto

    @GET("shows/{seriesId}/episodes")
    suspend fun getEpisodeList(
        @Path("seriesId") seriesId: Int
    ): List<EpisodeDto>

    @GET("episodes/{episodesId}")
    suspend fun getEpisodeDetails(
        @Path("episodesId") episodeId: Int
    ): EpisodeDetailsDto

    @GET("search/shows")
    suspend fun searchSeries(
        @Query("q") query: String
    ): List<SearchSeriesResultDto>
}
package com.example.tvmaze.repositories

import com.example.tvmaze.models.Episode
import com.example.tvmaze.models.EpisodeDetails
import com.example.tvmaze.models.Series
import com.example.tvmaze.models.SeriesDetails

interface SeriesRepository {
    suspend fun getSeriesList(page: Int): List<Series>
    suspend fun getSeriesDetails(seriesId: Int): SeriesDetails
    suspend fun getEpisodeList(seriesId: Int): List<Episode>
    suspend fun getEpisodeDetails(episodeId: Int): EpisodeDetails
    suspend fun searchSeries(name: String): List<Series>
}
package com.example.tvmaze.repositories

import com.example.tvmaze.models.*
import com.example.tvmaze.network.model.SeriesDtoMapper
import com.example.tvmaze.util.Constants
import com.example.tvmaze.webservices.SeriesService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class SeriesRepositoryImpl(
    private val mapper: SeriesDtoMapper,
): SeriesRepository {

    private val service = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(
            GsonConverterFactory.create(
        ))
        .client(
            OkHttpClient
                .Builder()
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .build()
        )
        .build()
        .create(SeriesService::class.java)

    override suspend fun getSeriesList(page: Int): List<Series> {
        val result = service.getSeriesList(page)
        return mapper.seriesListDtoToDomain(result)
    }

    override suspend fun getSeriesDetails(seriesId: Int): SeriesDetails {
        val result = service.getSeriesDetails(seriesId)
        return mapper.seriesDetailsDtoToDomain(result)
    }

    override suspend fun getEpisodeList(seriesId: Int): List<Episode> {
        val result = service.getEpisodeList(seriesId = seriesId)
        return mapper.episodeListDtoToDomain(result)
    }

    override suspend fun getEpisodeDetails(episodeId: Int): EpisodeDetails {
        val result = service.getEpisodeDetails(episodeId = episodeId)
        return mapper.episodeDetailsDtoToDomain(result)
    }

    override suspend fun searchSeries(name: String): List<Series> {
        val result = service.searchSeries(name)
        return mapper.searchSeriesResultDtoToDomain(result)
    }
}
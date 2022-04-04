package com.example.tvmaze.network.model

import com.example.tvmaze.models.*

class SeriesDtoMapper {
    fun seriesListDtoToDomain(initial: List<SeriesDto>?): List<Series> =
        initial?.map { seriesDtoToDomain(it) } ?: run { listOf() }

    private fun seriesDtoToDomain(initial: SeriesDto): Series =
        Series(
            id = initial.id,
            name = initial.name,
            seriesImage = SeriesImage(
                medium = initial.seriesImage?.medium,
                original = initial.seriesImage?.original
            )
        )

    fun seriesDetailsDtoToDomain(initial: SeriesDetailsDto): SeriesDetails =
        SeriesDetails(
            id = initial.id,
            name = initial.name,
            genres = initial.genres,
            schedule = Schedule(
                time = initial.schedule?.time,
                days = initial.schedule?.days,
            ),
            summary = initial.summary,
            seriesDetailsImage = SeriesDetailsImage(
                medium = initial.seriesDetailsImage?.medium,
                original = initial.seriesDetailsImage?.original
            ),
        )

    fun episodeListDtoToDomain(initial: List<EpisodeDto>?): List<Episode> =
        initial?.map { episodeDtoToDomain(it) } ?: run { listOf() }

    private fun episodeDtoToDomain(initial: EpisodeDto): Episode =
        Episode(
            id = initial.id,
            name = initial.name,
            season = initial.season,
            number = initial.number,
            episodeImage = EpisodeImage(
                medium = initial.episodeImage?.medium,
                original = initial.episodeImage?.original,
            )
        )

    fun episodeDetailsDtoToDomain(initial: EpisodeDetailsDto): EpisodeDetails =
        EpisodeDetails(
            id = initial.id,
            name = initial.name,
            season = initial.season,
            number = initial.number,
            summary = initial.summary,
            episodeDetailsImage = EpisodeDetailsImage(
                medium = initial.episodeDetailsImage?.medium,
                original = initial.episodeDetailsImage?.original,
            )
        )

    fun searchSeriesResultDtoToDomain(initial: List<SearchSeriesResultDto>?): List<Series> =
        initial?.map { seriesDtoToDomain(it.show) } ?: run { listOf() }
}
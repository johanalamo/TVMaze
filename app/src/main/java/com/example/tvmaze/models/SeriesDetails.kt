package com.example.tvmaze.models

data class SeriesDetails(
    val id: Int,
    val name: String,
    val genres: List<String>? = null,
    val schedule: Schedule? = null,
    val summary: String? = null,
    val seriesDetailsImage: SeriesDetailsImage? = null
)

data class SeriesDetailsImage(
    val medium: String? = null,
    val original: String? = null,
)

data class Schedule(
    val time: String? = "",
    val days: List<String>? = null,
)
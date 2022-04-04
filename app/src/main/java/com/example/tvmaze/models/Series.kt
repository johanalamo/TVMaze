package com.example.tvmaze.models

data class Series(
    val id: Int,
    val name: String,
    val seriesImage: SeriesImage? = null
)

data class SeriesImage(
    val medium: String? = null,
    val original: String? = null,
)
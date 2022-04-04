package com.example.tvmaze.models

data class Episode(
    val id: Int,
    val name: String,
    val season: Int = 0,
    val number: Int = 0,
    val episodeImage: EpisodeImage? = null
)

data class EpisodeImage(
    val medium: String? = null,
    val original: String? = null,
)
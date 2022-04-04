package com.example.tvmaze.models

data class EpisodeDetails(
    val id: Int,
    val name: String,
    val season: Int = 0,
    val number: Int = 0,
    val summary: String? = null,
    val episodeDetailsImage: EpisodeDetailsImage? = null
)

data class EpisodeDetailsImage(
    val medium: String? = null,
    var original: String? = null,
)
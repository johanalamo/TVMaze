package com.example.tvmaze.network.model

import com.google.gson.annotations.SerializedName

class EpisodeDetailsDto (
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("season") val season: Int = 0,
    @SerializedName("number") val number: Int = 0,
    @SerializedName("summary") val summary: String? = null,
    @SerializedName("image") val episodeDetailsImage: EpisodeDetailsImageDto? = null
)

class EpisodeDetailsImageDto (
    @SerializedName("medium") val medium: String? = null,
    @SerializedName("original") var original: String? = null,
)
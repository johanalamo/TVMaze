package com.example.tvmaze.network.model

import com.google.gson.annotations.SerializedName

class EpisodeDto(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("season") val season: Int = 0,
    @SerializedName("number") val number: Int = 0,
    @SerializedName("image") val episodeImage: EpisodeImageDto? = null
)

class EpisodeImageDto (
    @SerializedName("medium") val medium: String? = null,
    @SerializedName("original") var original: String? = null,
)
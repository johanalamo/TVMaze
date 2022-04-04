package com.example.tvmaze.network.model

import com.google.gson.annotations.SerializedName

class SeriesDto(
    @SerializedName("id") var id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("image") val seriesImage: SeriesImageDto? = null
)

class SeriesImageDto (
    @SerializedName("medium") val medium: String? = null,
    @SerializedName("original") var original: String? = null,
)
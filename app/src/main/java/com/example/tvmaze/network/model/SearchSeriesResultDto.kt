package com.example.tvmaze.network.model

import com.google.gson.annotations.SerializedName

class SearchSeriesResultDto(
    @SerializedName("score") val score: Double,
    @SerializedName("show") val show: SeriesDto,
)
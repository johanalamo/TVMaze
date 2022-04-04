package com.example.tvmaze.network.model

import com.google.gson.annotations.SerializedName

class SeriesDetailsDto(
    @SerializedName("id") var id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("genres") val genres: List<String>? = null,
    @SerializedName("schedule") val schedule: ScheduleDto? = null,
    @SerializedName("summary") val summary: String? = null,
    @SerializedName("image") val seriesDetailsImage: SeriesDetailsImageDto? = null,
)

class SeriesDetailsImageDto (
    @SerializedName("medium") val medium: String? = null,
    @SerializedName("original") var original: String? = null,
)

class ScheduleDto(
    @SerializedName("time") val time: String? = "",
    @SerializedName("days") val days: List<String>? = null,
)
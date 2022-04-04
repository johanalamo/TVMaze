package com.example.tvmaze.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tvmaze.models.Episode
import com.example.tvmaze.models.EpisodeDetails
import com.example.tvmaze.models.Series
import com.example.tvmaze.models.SeriesDetails
import com.example.tvmaze.network.model.SeriesDtoMapper
import com.example.tvmaze.repositories.SeriesRepository
import com.example.tvmaze.repositories.SeriesRepositoryImpl
import com.example.tvmaze.util.Constants
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    // Repository
    private var _repository: SeriesRepository = SeriesRepositoryImpl(
        mapper = SeriesDtoMapper(),
    )

    // ********* Series List section ******************
    val isLoadingSeriesList = mutableStateOf(false)
    val seriesList: MutableState<List<Series>> = mutableStateOf(listOf())
    private var pageToLoad: Int = 0
    private var _allSeriesLoaded: Boolean = false
    val totalSeriesLoaded: Int
        get() = seriesList.value.size

    fun loadFirstSeriesListPage() {
        if (totalSeriesLoaded == 0) {
            loadNextSeriesListPage()
        }
    }

    fun loadNextSeriesListPage() {
        isLoadingSeriesList.value = true
        viewModelScope.launch {
            val list: List<Series> = _repository.getSeriesList(pageToLoad)

            // if to simulate a slow network
            if (Constants.DEBUG) delay(Constants.DELAY)

            if (list.isNotEmpty()) {
                pageToLoad += 1
                seriesList.value += list
            } else {
                _allSeriesLoaded = true
            }
            isLoadingSeriesList.value = false
        }
    }

    // ********* Series Details section ******************
    val isLoadingSeriesDetails = mutableStateOf(false)
    val seriesDetails: MutableState<SeriesDetails?> = mutableStateOf(null)
    fun loadSeriesDetails(seriesId: Int) {
        isLoadingSeriesDetails.value = true
        viewModelScope.launch {
            if (Constants.DEBUG) delay(Constants.DELAY)
            seriesDetails.value = _repository.getSeriesDetails(seriesId)
            isLoadingSeriesDetails.value = false
        }
    }

    // ********* Episode List section ******************
    val isLoadingEpisodeList = mutableStateOf(false)
    val episodeList: MutableState<List<Episode>> = mutableStateOf(listOf())
    fun loadEpisodeList(seriesId: Int) {
        isLoadingEpisodeList.value = true
        viewModelScope.launch {
            if (Constants.DEBUG) delay(Constants.DELAY * 3)
            episodeList.value = _repository.getEpisodeList(seriesId)
            isLoadingEpisodeList.value = false
        }
    }

    // ********* Episode details section ******************
    val isLoadingEpisodeDetails = mutableStateOf(false)
    val episodeDetails: MutableState<EpisodeDetails?> = mutableStateOf(null)
    fun loadEpisodeDetails(episodeId: Int) {
        isLoadingEpisodeDetails.value = true
        viewModelScope.launch {
            if (Constants.DEBUG) delay(Constants.DELAY)
            episodeDetails.value = _repository.getEpisodeDetails(episodeId)
            isLoadingEpisodeDetails.value = false
        }
    }

    // ********** Searching series section ********************
    val isSearchingSeries = mutableStateOf(false)
    val searchingSeriesQuery = mutableStateOf("")
    val searchingSeriesListResult: MutableState<List<Series>> = mutableStateOf(listOf())
    fun searchSeries() {
        isSearchingSeries.value = true
        viewModelScope.launch {
            if (Constants.DEBUG) {
                delay(Constants.DELAY)
            }
            searchingSeriesListResult.value = _repository.searchSeries(searchingSeriesQuery.value)
            isSearchingSeries.value = false
        }
    }
}
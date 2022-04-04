package com.example.tvmaze.ui

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import kotlin.collections.ArrayDeque

enum class Screen {
    SHOWING_SERIES_LIST,
    SEARCHING_SERIES,
    SHOWING_SERIES_DETAILS,
    SHOWING_EPISODE_DETAILS,
}

class UIState(
    initScreen: Screen
) {
    // ***** section for managing navigation ********
    private var screenStack = ArrayDeque<Screen>()
    private var _currentScreen: MutableState<Screen> =
        mutableStateOf(initScreen) //  = remember { mutableStateOf(Screen.SHOWING_SERIES_LIST) }
    fun getCurrentScreen(): MutableState<Screen> = _currentScreen
    init {
        screenStack.addFirst(initScreen)
    }

    fun moveScreenTo(screen: Screen) {
        screenStack.addFirst(screen)
        _currentScreen.value = screen
    }

    fun moveScreenBack() {
        screenStack.removeFirst()
        _currentScreen.value = screenStack.first()
    }

    // Section to keep scrolling information in different screens
    val seriesListScrollState: MutableState<LazyListState> = mutableStateOf(LazyListState(0, 0))
    val episodeListScrollState: MutableState<LazyListState> = mutableStateOf(LazyListState(0, 0))
    val searchingSeriesResultScrollState: MutableState<LazyListState> =
        mutableStateOf(LazyListState(0, 0))
}
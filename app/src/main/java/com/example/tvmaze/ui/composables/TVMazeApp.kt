package com.example.tvmaze.ui.composables

import androidx.compose.runtime.Composable
import com.example.tvmaze.models.AppContainer
import com.example.tvmaze.ui.theme.TVMazeTheme
import com.example.tvmaze.ui.Screen

@Composable
fun TVMazeApp(appContainer: AppContainer) {
    TVMazeTheme {
        when (appContainer.uiState.getCurrentScreen().value) {
            Screen.SHOWING_SERIES_LIST -> SeriesListScaffold(appContainer = appContainer)
            Screen.SHOWING_SERIES_DETAILS -> SeriesDetailsScaffold(appContainer = appContainer)
            Screen.SHOWING_EPISODE_DETAILS -> EpisodeDetailsScaffold(appContainer = appContainer)
            Screen.SEARCHING_SERIES -> SearchingScaffold(appContainer = appContainer)
        }
    }
}
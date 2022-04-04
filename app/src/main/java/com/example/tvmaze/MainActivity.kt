package com.example.tvmaze

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tvmaze.models.AppContainer
import com.example.tvmaze.ui.composables.TVMazeApp
import com.example.tvmaze.ui.Screen
import com.example.tvmaze.ui.UIState

class MainActivity : ComponentActivity() {

    lateinit var appContainer: AppContainer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val uiState = UIState(Screen.SHOWING_SERIES_LIST)
        setContent {
            appContainer = AppContainer(
                applicationContext = this,
                viewModel = viewModel(),
                uiState = uiState
            )
            appContainer.viewModel.loadFirstSeriesListPage()
            TVMazeApp(appContainer)
        }
    }

    override fun onBackPressed() {
        when (appContainer.uiState.getCurrentScreen().value) {
            Screen.SHOWING_SERIES_LIST -> super.onBackPressed()
            else -> appContainer.uiState.moveScreenBack()
        }
    }
}
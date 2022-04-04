package com.example.tvmaze.models

import android.content.Context
import com.example.tvmaze.ui.UIState
import com.example.tvmaze.viewmodels.MainViewModel

class AppContainer(
    val applicationContext: Context,
    val viewModel: MainViewModel,
    val uiState: UIState,
)

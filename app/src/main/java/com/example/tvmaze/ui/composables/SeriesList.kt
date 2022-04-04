package com.example.tvmaze.ui.composables

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.tvmaze.R
import com.example.tvmaze.models.AppContainer
import com.example.tvmaze.ui.Screen

@Composable
fun SeriesListScaffold(appContainer: AppContainer) {
    Scaffold(
        topBar = { SeriesListTopBar(appContainer = appContainer) },
        content = { SeriesListContent(appContainer = appContainer) },
    )
}

@Composable
fun SeriesListTopBar(appContainer: AppContainer) {
    TopAppBar(
        title = {
            Row() {
                Icon(
                    imageVector = Icons.Filled.Movie,
                    contentDescription = stringResource(id = R.string.app_name)
                )
                Spacer(modifier = Modifier.padding(start = 6.dp))
                Text(text = stringResource(id = R.string.app_name))
            }
        },
        elevation = 10.dp,
        modifier = Modifier,
        actions = {
            Row(modifier = Modifier.padding(end = 8.dp)) {
                Button(onClick = {
                    appContainer.uiState.moveScreenTo(Screen.SEARCHING_SERIES)
                }) {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = "Search for a series"
                    )
                }
            }
        }
    )
}

@Composable
fun SeriesListContent(appContainer: AppContainer) {
    Column {
        if (appContainer.viewModel.seriesList.value.isNullOrEmpty()) {
            Loader(stringResource(id = R.string.loading_series_list_first_page))
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxWidth(1f),
                state = appContainer.uiState.seriesListScrollState.value
            ) {
                itemsIndexed(
                    items = appContainer.viewModel.seriesList.value,
                ) { index, item ->
                    Series(appContainer = appContainer, series = item)
                    if (index == (appContainer.viewModel.totalSeriesLoaded - 1)) {
                        appContainer.viewModel.loadNextSeriesListPage()
                    }
                }
                item {
                    if (appContainer.viewModel.isLoadingSeriesList.value) {
                        Loader(stringResource(id = R.string.loading_series_list_next_page))

                    }
                }
            }
        }
    }
}

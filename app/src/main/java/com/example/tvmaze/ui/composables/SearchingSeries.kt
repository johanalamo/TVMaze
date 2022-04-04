package com.example.tvmaze.ui.composables

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.tvmaze.R
import com.example.tvmaze.models.AppContainer

@Composable
fun SearchingScaffold(appContainer: AppContainer) {
    Scaffold(
        topBar = { SearchingTopBar(appContainer = appContainer) },
        content = { SearchingContent(appContainer = appContainer) },
    )
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchingTopBar(appContainer: AppContainer) {
    TopAppBar(
        navigationIcon = {
            IconButton(onClick = {
                appContainer.uiState.moveScreenBack()
            }) {
                Icon(Icons.Filled.ArrowBack, "backIcon")
            }
        },
        title = {},
        elevation = 10.dp,
        modifier = Modifier,
        actions = {
            var showClearButton by remember { mutableStateOf(false) }
            val keyboardController = LocalSoftwareKeyboardController.current
            val focusRequester = remember { FocusRequester() }
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 2.dp)
                    .onFocusChanged { focusState ->
                        showClearButton = (focusState.isFocused)
                    }
                    .focusRequester(focusRequester),
                value = appContainer.viewModel.searchingSeriesQuery.value,
                onValueChange = {
                    appContainer.viewModel.searchingSeriesQuery.value = it
                    appContainer.viewModel.searchSeries()
                },
                placeholder = {
                    Text(text = stringResource(id = R.string.write_the_name_of_the_series))
                },
                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = MaterialTheme.colors.primary,
                    unfocusedIndicatorColor = Color.Transparent,
                    backgroundColor = Color.White.copy(alpha = 0.3f),
                    cursorColor = LocalContentColor.current.copy(alpha = LocalContentAlpha.current)
                ),
                trailingIcon = {
                    AnimatedVisibility(
                        visible = showClearButton,
                        enter = fadeIn(),
                        exit = fadeOut()
                    ) {
                        IconButton(onClick = {
                            appContainer.viewModel.searchingSeriesQuery.value = ""
                        }) {
                            Icon(
                                imageVector = Icons.Filled.Close,
                                contentDescription = ""
                            )
                        }

                    }
                },
                maxLines = 1,
                singleLine = true,
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(onDone = {
                    keyboardController?.hide()
                }),
            )
        }
    )
}

@Composable
fun SearchingContent(appContainer: AppContainer) {
    Column {
        if (appContainer.viewModel.searchingSeriesQuery.value.isNullOrBlank()) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .fillMaxHeight(1f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = stringResource(id = R.string.waiting_to_search),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(vertical = 6.dp),
                )
            }
        } else if (appContainer.viewModel.isSearchingSeries.value) {
            Loader(stringResource(id = R.string.searching_for_results))
        } else if (appContainer.viewModel.searchingSeriesListResult.value.isNullOrEmpty()) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .fillMaxHeight(1f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = stringResource(id = R.string.there_are_no_results),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(vertical = 6.dp),
                )
            }
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxWidth(1f),
                state = appContainer.uiState.searchingSeriesResultScrollState.value
            ) {
                itemsIndexed(
                    items = appContainer.viewModel.searchingSeriesListResult.value,
                ) { index, item ->
                    Series(appContainer = appContainer, series = item)
                }
            }
        }
    }
}

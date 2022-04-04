package com.example.tvmaze.ui.composables

import android.os.Build
import android.text.Html
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.example.tvmaze.R
import com.example.tvmaze.models.AppContainer
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun SeriesDetailsScaffold(appContainer: AppContainer) {
    Scaffold(
        topBar = { SeriesDetailsTopBar(appContainer = appContainer) },
        content = { SeriesDetailsContent(appContainer = appContainer) }
    )
}

@Composable
fun SeriesDetailsTopBar(appContainer: AppContainer) {
    TopAppBar(
        navigationIcon = {
            IconButton(onClick = {
                appContainer.uiState.moveScreenBack()
            }) {
                Icon(Icons.Filled.ArrowBack, "backIcon")
            }
        },
        title = {
            Row() {
                appContainer.viewModel.seriesDetails.value?.let {
                    Text(text = it.name)
                }
            }
        },
        elevation = 10.dp,
        modifier = Modifier,
        actions = {
            Row(modifier = Modifier.padding(end = 8.dp)) {
                Button(onClick = {
                    message(appContainer, "Favorite feature is in construction")
                }) {
                    Icon(
                        imageVector = Icons.Outlined.Favorite,
                        contentDescription = "is favorite"
                    )
                }
            }
        }
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SeriesDetailsContent(
    appContainer: AppContainer,
) {
    if (appContainer.viewModel.isLoadingSeriesDetails.value) {
        Loader(stringResource(id = R.string.loading_series_details))
    } else {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth(1f)
                .padding(20.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            state = appContainer.uiState.episodeListScrollState.value
        ) {
            appContainer.viewModel.seriesDetails.value?.let {
                stickyHeader() {
                    Text(
                        text = it.name,
                        style = typography.h4,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth()
                            .background(
                                brush = Brush.horizontalGradient(
                                    listOf(
                                        MaterialTheme.colors.primary,
                                        MaterialTheme.colors.secondary
                                    ),
                                ),
                                alpha = 0.7f,
                                shape = RoundedCornerShape(6.dp)
                            )
                    )
                }

                item {
                    GlideImage(
                        imageModel = it.seriesDetailsImage?.original,
                        contentScale = ContentScale.Fit,
                        modifier = Modifier
                            .fillMaxWidth(0.8f)
                            .fillMaxHeight(0.4f)
                            .padding(4.dp)
                    )
                }
                it.genres?.let { genresList ->
                    item {
                        val genresString = buildAnnotatedString {
                            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                                append(stringResource(id = R.string.genres) + ": ")
                            }
                            append(genresList.joinToString(", "))
                        }
                        Text(
                            text = genresString,
                            style = typography.body1,
                            modifier = Modifier
                                .padding(top = 4.dp)
                                .fillMaxWidth(1f),
                            textAlign = TextAlign.Justify,
                        )
                    }
                }
                it.schedule?.let { schedule ->
                    item {
                        val scheduleString = buildAnnotatedString {
                            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                                append(stringResource(id = R.string.schedule) + ": ")
                            }
                            append(
                                "${schedule.time} (${schedule.days?.joinToString(", ")} )"
                            )
                        }

                        Text(
                            text = scheduleString,
                            style = typography.body1,
                            modifier = Modifier
                                .padding(top = 4.dp)
                                .fillMaxWidth(1f),
                            textAlign = TextAlign.Justify
                        )
                    }
                }
                it.summary?.let {
                    item {
                        val summaryText = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                            Html.fromHtml(it, 0)
                        } else {
                            Html.fromHtml(it)
                        }
                        val summaryAnnotatedString = buildAnnotatedString {
                            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                                append(stringResource(id = R.string.summary) + ": ")
                            }
                            append(summaryText.toString())
                        }
                        Text(
                            text = summaryAnnotatedString,
                            style = typography.body2,
                            modifier = Modifier
                                .padding(top = 4.dp)
                                .fillMaxWidth(1f),
                            textAlign = TextAlign.Justify
                        )
                    }
                }

                item {
                    Text(
                        text = stringResource(id = R.string.seasons_and_episodes),
                        style = typography.h5,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold,
                    )
                }

                if (appContainer.viewModel.isLoadingEpisodeList.value) {
                    item {
                        Loader(stringResource(id = R.string.loading_seasons_and_episodes))
                    }
                } else {
                    val seasons = appContainer.viewModel.episodeList.value.map { episode ->
                        episode.season
                    }.toSet()
                    seasons.forEach { season ->
                        stickyHeader {
                            Text(
                                text = stringResource(id = R.string.season, season),
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .padding(16.dp)
                                    .fillMaxWidth()
                                    .background(
                                        brush = Brush.horizontalGradient(
                                            listOf(
                                                MaterialTheme.colors.primary,
                                                MaterialTheme.colors.secondary
                                            ),
                                        ),
                                        alpha = 0.7f,
                                        shape = RoundedCornerShape(6.dp)
                                    )
                            )
                        }
                        itemsIndexed(
                            items = appContainer.viewModel.episodeList.value
                                .filter { episode ->
                                    episode.season == season
                                },
                        ) { index, item ->
                            Episode(appContainer, item)
                        }
                    }
                }
            }
        }
    }
}
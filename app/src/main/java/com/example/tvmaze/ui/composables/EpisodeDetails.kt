package com.example.tvmaze.ui.composables

import android.os.Build
import android.text.Html
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
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
fun EpisodeDetailsScaffold(appContainer: AppContainer) {
    Scaffold(
        topBar = { EpisodeDetailsTopBar(appContainer = appContainer) },
        content = { EpisodeDetailsContent(appContainer = appContainer) }
    )
}

@Composable
fun EpisodeDetailsTopBar(appContainer: AppContainer) {
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
                appContainer.viewModel.episodeDetails.value?.let {
                    Text(text = it.name)
                }
            }
        },
        elevation = 10.dp,
    )
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun EpisodeDetailsContent(appContainer: AppContainer) {

    Box(modifier = Modifier.verticalScroll(rememberScrollState(0), true)) {
        Column(
            modifier = Modifier
                .fillMaxWidth(1f)
                .padding(20.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            // Series name
            appContainer.viewModel.seriesDetails.value?.let { it1 ->
                Text(
                    text = it1.name,
                    style = MaterialTheme.typography.h4,
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
            if (appContainer.viewModel.isLoadingEpisodeDetails.value) {
                Loader(stringResource(id = R.string.loading_episode_details))
            } else {
                appContainer.viewModel.episodeDetails.value?.let {

                    // season number
                    Text(
                        text = buildAnnotatedString {
                            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                                append(stringResource(id = R.string.season_number) + ": ")
                            }
                            append(it.season.toString())
                        },
                        style = MaterialTheme.typography.body1,
                        modifier = Modifier
                            .padding(top = 4.dp)
                            .fillMaxWidth(1f),
                        textAlign = TextAlign.Justify,
                    )

                    // episode number
                    Text(
                        text = buildAnnotatedString {
                            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                                append(stringResource(id = R.string.episode_number) + ": ")
                            }
                            append(it.number.toString())
                        },
                        style = MaterialTheme.typography.body1,
                        modifier = Modifier
                            .padding(top = 4.dp)
                            .fillMaxWidth(1f),
                        textAlign = TextAlign.Justify,
                    )

                    // episode name
                    Text(
                        text = buildAnnotatedString {
                            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                                append(stringResource(id = R.string.episode_name) + ": ")
                            }
                            append(it.name)
                        },
                        style = MaterialTheme.typography.body1,
                        modifier = Modifier
                            .padding(top = 4.dp)
                            .fillMaxWidth(1f),
                        textAlign = TextAlign.Justify,
                    )

                    // summary
                    it.summary?.let { it1 ->
                        val summaryText = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                            Html.fromHtml(it1, 0)
                        } else {
                            Html.fromHtml(it1)
                        }
                        val summaryAnnotatedString = buildAnnotatedString {
                            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                                append(stringResource(id = R.string.summary) + ": ")
                            }
                            append(summaryText.toString())
                        }
                        Text(
                            text = summaryAnnotatedString,
                            style = MaterialTheme.typography.body2,
                            modifier = Modifier
                                .padding(top = 4.dp)
                                .fillMaxWidth(1f),
                            textAlign = TextAlign.Justify
                        )
                    }

                    GlideImage(
                        imageModel = it.episodeDetailsImage?.original,
                        contentScale = ContentScale.Fit,
                        modifier = Modifier
                            .fillMaxWidth(0.9f)
                            .padding(4.dp),
                    )
                }
            }
        }
    }
}
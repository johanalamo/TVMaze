package com.example.tvmaze.ui.composables

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.tvmaze.models.AppContainer
import com.example.tvmaze.models.Episode
import com.example.tvmaze.ui.Screen
import com.skydoves.landscapist.glide.GlideImage

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Episode(appContainer: AppContainer, item: Episode) {
    Card(
        border = BorderStroke(1.dp, MaterialTheme.colors.secondary),
        shape = RoundedCornerShape(4.dp),
        modifier = Modifier
            .fillMaxWidth()
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessLow
                )
            ),
        onClick = {
            appContainer.viewModel.loadEpisodeDetails(item.id)
            appContainer.uiState.moveScreenTo(Screen.SHOWING_EPISODE_DETAILS)
        }
    ) {
        Row {
            GlideImage(
                imageModel = item.episodeImage?.medium,
                contentScale = ContentScale.Inside,
                modifier = Modifier
                    .height(40.dp)
                    .width(40.dp)
                    .padding(4.dp)
            )
            Text(
                text = "${item.number}: ${item.name}",
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .fillMaxWidth(1f),
            )
        }
    }
}
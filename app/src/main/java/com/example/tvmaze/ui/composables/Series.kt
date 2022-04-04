package com.example.tvmaze.ui.composables

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.example.tvmaze.models.AppContainer
import com.example.tvmaze.models.Series
import com.example.tvmaze.ui.Screen
import com.skydoves.landscapist.glide.GlideImage

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Series(
    appContainer: AppContainer,
    series: Series,
) {
    Card(
        border = BorderStroke(1.dp, MaterialTheme.colors.primary),
        shape = RoundedCornerShape(4.dp),
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessLow
                )
            )
            .shadow(20.dp, RoundedCornerShape(4.dp)),
        onClick = {
            appContainer.viewModel.loadSeriesDetails(series.id)
            appContainer.viewModel.loadEpisodeList(series.id)
            appContainer.uiState.moveScreenTo(Screen.SHOWING_SERIES_DETAILS)
        }
    ) {
        Row {
            GlideImage(
                imageModel = series.seriesImage?.medium,
                contentScale = ContentScale.Inside,
                modifier = Modifier
                    .height(60.dp)
                    .width(60.dp)
                    .padding(4.dp)
            )
            Text(
                text = "${series.id}: ${series.name}",
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .fillMaxWidth(0.90f),
            )

            // for selected
//            Icon(
//                imageVector = Icons.Outlined.Favorite, // .Outlined.Favorite,
//                contentDescription = "is favorite",
//                modifier = Modifier.align(Alignment.CenterVertically),
//                tint = MaterialTheme.colors.primary,
//            )
            // for unselected
            Icon(
                imageVector = Icons.TwoTone.Favorite, // .Outlined.Favorite,
                contentDescription = "is favorite",
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(8.dp)
                    .clickable { message(appContainer, "Favorite feature is in construction") },
                tint = MaterialTheme.colors.primary,
            )
        }
    }
}

package br.com.renankoji.pockettravel.screens

import FormSearchPlaces
import android.icu.text.DecimalFormat
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.com.renankoji.pockettravel.components.CardImagePlaces
import br.com.renankoji.pockettravel.components.DashboardHeader
import br.com.renankoji.pockettravel.repository.ImagesTopVisited
import br.com.renankoji.pockettravel.R
import br.com.renankoji.pockettravel.api.calculateDistance
import br.com.renankoji.pockettravel.repository.ImagesLastSeenPlaces
import java.util.Locale

@Composable
fun DashboardScreen(
    userLatitude: Double?,
    userLongitude: Double?,
    hasLocationPermission: Boolean,
    navController: NavController
) {
    val imagesTopPlaces = ImagesTopVisited()
    val placesLastSeen = ImagesLastSeenPlaces()
    val scrollState = rememberScrollState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                colorResource(id = R.color.background_color),
            )
            .verticalScroll(scrollState)
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            if (!hasLocationPermission) {
                Text(text = "Permissão de localização não concedida.")
            }
            // Header
            DashboardHeader()
            Spacer(modifier = Modifier.height(16.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
            ) {
                Card(
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(
                            RoundedCornerShape(
                                topStart = 36.dp,
                                topEnd = 36.dp,
                                bottomStart = 0.dp,
                                bottomEnd = 0.dp
                            )
                        )
                        .padding(0.dp),
                    colors = CardDefaults
                        .cardColors(colorResource(id = R.color.background_color_light)),
                ) {
                    Column {
                        Spacer(modifier = Modifier.height(16.dp))
                        // Search Form
                        FormSearchPlaces(navController)

                        // Places Card
                        Column(
                            modifier = Modifier
                                .background(colorResource(id = R.color.primary_200))
                                .fillMaxSize()
                        ) {
                            Spacer(modifier = Modifier.height(12.dp))

                            //most visited
                            Text(
                                text = stringResource(id = R.string.dashboard_most_visited).uppercase(
                                    Locale.getDefault()
                                ),
                                modifier = Modifier
                                    .padding(horizontal = 32.dp)
                                    .fillMaxWidth(),
                                color = colorResource(id = R.color.title_color),
                                fontSize = 18.sp,
                                textAlign = TextAlign.Start,
                                fontWeight = FontWeight.Bold,
                                letterSpacing = 1.sp
                            )
                            LazyRow(contentPadding = PaddingValues(horizontal = 32.dp)) {
                                items(imagesTopPlaces) { card ->
                                    CardImagePlaces(
                                        painter = painterResource(id = card.imageResId),
                                        contentDescription = null,
                                        modifier = Modifier
                                            .width(200.dp)
                                            .height(180.dp)
                                            .padding(end = 8.dp)
                                            .clip(RoundedCornerShape(16.dp)),
                                        contentScale = ContentScale.Crop
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.height(20.dp))

                            //last seen list
                            Text(
                                text = stringResource(id = R.string.dashboard_lastseen).uppercase(
                                    Locale.getDefault()
                                ),
                                modifier = Modifier
                                    .padding(horizontal = 32.dp)
                                    .fillMaxWidth(),
                                color = colorResource(id = R.color.title_color),
                                fontSize = 18.sp,
                                textAlign = TextAlign.Start,
                                fontWeight = FontWeight.Bold,
                                letterSpacing = 1.sp
                            )

                            Spacer(modifier = Modifier.height(8.dp))

                            // Cards
                            LazyRow(
                                contentPadding = PaddingValues(horizontal = 32.dp),
                            ) {
                                items(placesLastSeen) { placeLastSeen ->
                                    var formattedDistance = "Não disponível"

                                    if (userLatitude != null && userLongitude != null) {
                                        val dist = calculateDistance(
                                            userLatitude,
                                            userLongitude,
                                            placeLastSeen.latitude,
                                            placeLastSeen.longitude
                                        )
                                        val format = DecimalFormat("#,###.00")
                                        formattedDistance = format.format(dist)
                                    }
                                    Column {
                                        CardImagePlaces(
                                            painter = painterResource(id = placeLastSeen.imageResId),
                                            contentDescription = null,
                                            modifier = Modifier
                                                .width(200.dp)
                                                .height(180.dp)
                                                .padding(end = 8.dp)
                                                .clip(RoundedCornerShape(16.dp)),
                                            contentScale = ContentScale.Crop
                                        )
                                        Text(text = placeLastSeen.location)
                                        Text(text = "Distância: $formattedDistance km")
                                    }
                                }
                            }
                            Spacer(modifier = Modifier.height(20.dp))
                        }
                    }
                }
            }
        }
    }
}
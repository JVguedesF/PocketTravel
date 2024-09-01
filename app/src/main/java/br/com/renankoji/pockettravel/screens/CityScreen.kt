package br.com.renankoji.pockettravel.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import br.com.renankoji.pockettravel.R
import br.com.renankoji.pockettravel.api.CitiesSearchViewModelViewModel
import br.com.renankoji.pockettravel.components.CardCityInfo
import br.com.renankoji.pockettravel.components.DashboardHeader
import br.com.renankoji.pockettravel.repository.CityScreenBackgrounds

@Composable
fun CityScreen(
    city: String,
    category: String,
    userLatitude: Double?,
    userLongitude: Double?,
    citiesSearchViewModelViewModel: CitiesSearchViewModelViewModel = viewModel()
) {
    LaunchedEffect(city, category, userLatitude, userLongitude) {
        citiesSearchViewModelViewModel.searchPlaces(
            city = city,
            category = category,
            userLat = userLatitude,
            userLon = userLongitude
        )
    }

    val places = citiesSearchViewModelViewModel.places // List<Place>
    val errorMessage = citiesSearchViewModelViewModel.errorMessage
    val backgrounds = CityScreenBackgrounds()
    val cityBackground = backgrounds.firstOrNull { it.title == city }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        //check if the city was found
        cityBackground?.let { city ->
            //background
            Image(
                painter = city.painter,
                contentDescription = "Background Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .align(Alignment.Center)
            )

            Column(modifier = Modifier.fillMaxSize()) {
                //city title
                Spacer(modifier = Modifier.height(60.dp))
                Text(
                    text = city.title,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 10.dp, end = 10.dp),
                    color = Color.White,
                    style = TextStyle(
                        fontSize = 50.sp,
                        lineHeight = 35.sp,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold
                    )
                )
                Spacer(modifier = Modifier.height(80.dp))


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
                    Column(modifier = Modifier.padding(16.dp)) {
                        errorMessage?.let {
                            Text(text = it, color = Color.Red)
                        }

                        LazyColumn {
                            items(places) { place ->
                                Spacer(modifier = Modifier.height(8.dp))
                                CardCityInfo(local = place.name, distance = place.distance)
                            }
                        }
                    }
                }
            }
        }
    }
}
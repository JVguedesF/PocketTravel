package br.com.renankoji.pockettravel.api

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.math.*

class CitiesSearchViewModelViewModel : ViewModel() {
    private val _places = mutableStateListOf<Place>()
    val places: List<Place> get() = _places

    private val _errorMessage = mutableStateOf<String?>(null)
    val errorMessage: String? get() = _errorMessage.value


    fun searchPlaces(city: String, category: String, userLat: Double?, userLon: Double?) {
        _places.clear()
        _errorMessage.value = null
        viewModelScope.launch {
            searchGeonames(city, category, userLat, userLon)
        }
    }

    fun setErrorMessage(message: String) {
        _errorMessage.value = message
    }

    private fun searchGeonames(city: String, category: String, userLat: Double?, userLon: Double?) {
        ApiClient.geonamesApi.searchPlaces(city, 7, "kojirenan", featureClass = category)
            .enqueue(object : Callback<GeonamesResponse> {
                override fun onResponse(
                    call: Call<GeonamesResponse>,
                    response: Response<GeonamesResponse>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.geonames?.forEach { place ->
                            var distance = 0.0
                            if (userLat != null && userLon != null) {
                                distance = calculateDistance(
                                    userLat,
                                    userLon,
                                    place.lat.toDouble(),
                                    place.lng.toDouble()
                                )
                            }
                            val newPlace = Place(
                                name = place.name,
                                distance = distance / 1000
                            )

                            _places.add(newPlace)
                        }
                    } else {
                        _errorMessage.value = "Erro ao buscar localizações: ${response.message()}"
                    }
                }

                override fun onFailure(call: Call<GeonamesResponse>, t: Throwable) {
                    _errorMessage.value = "Erro ao buscar localizações: ${t.message}"
                }
            })
    }
}

data class Place(
    val name: String,
    val distance: Double
)
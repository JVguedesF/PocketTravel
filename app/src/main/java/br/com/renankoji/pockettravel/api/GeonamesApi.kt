package br.com.renankoji.pockettravel.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GeonamesApi {
    @GET("searchJSON")
    fun searchPlaces(
        @Query("q") query: String,
        @Query("maxRows") maxRows: Int,
        @Query("username") username: String,
        @Query("featureClass") featureClass: String? = null,
        @Query("featureCode") featureCode: String? = null
    ): Call<GeonamesResponse>
}

data class GeonamesResponse(
    val geonames: List<Geoname>
)

data class Geoname(
    val name: String,
    val countryName: String,
    val lat: String,
    val lng: String
)

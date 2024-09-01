package br.com.renankoji.pockettravel.utils

import kotlin.math.*

fun calculateDistance(
    userLat: Double,
    userLon: Double,
    apiLat: Double,
    apiLon: Double
): Double {
    val earthRadius = 6371e3 // Raio da Terra em metros

    val dLat = Math.toRadians(apiLat - userLat)
    val dLon = Math.toRadians(apiLon - userLon)

    val a = sin(dLat / 2) * sin(dLat / 2) +
            cos(Math.toRadians(userLat)) * cos(Math.toRadians(apiLat)) *
            sin(dLon / 2) * sin(dLon / 2)

    val c = 2 * atan2(sqrt(a), sqrt(1 - a))

    return earthRadius * c
}

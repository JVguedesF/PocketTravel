package br.com.renankoji.pockettravel.model

import androidx.compose.ui.graphics.painter.Painter

data class CityScreenList(
    val id: Long = 0,
    val title: String = "",
    val painter: Painter
)
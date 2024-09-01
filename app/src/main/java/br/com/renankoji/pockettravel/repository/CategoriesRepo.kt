package br.com.renankoji.pockettravel.repository

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddRoad
import androidx.compose.material.icons.filled.Apartment
import androidx.compose.material.icons.filled.Forest
import androidx.compose.material.icons.filled.LocationCity
import androidx.compose.material.icons.filled.NaturePeople
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.WaterfallChart
import androidx.compose.ui.graphics.vector.ImageVector

data class Categories (
    val id: Int,
    val title: String,
    val featureClass: String,
    val icon: ImageVector
)

fun CategoriesRepo(): List<Categories> {
    return listOf(
        Categories(id = 0, title = "Cidade ou Vila", featureClass = "P", icon = Icons.Filled.LocationCity),
        Categories(id = 2, title = "Corpo d'água", featureClass = "H", icon = Icons.Filled.WaterfallChart),
        Categories(id = 3, title = "Parques", featureClass = "L", icon = Icons.Filled.Forest),
        Categories(id = 4, title = "Pontos de interesse", featureClass = "S", icon = Icons.Filled.Place),
        Categories(id = 5, title = "Montanha ou elevação", featureClass = "T", icon = Icons.Filled.NaturePeople),
    )
}
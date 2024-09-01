package br.com.renankoji.pockettravel.repository

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalance
import androidx.compose.material.icons.filled.BeachAccess
import androidx.compose.material.icons.filled.Museum
import androidx.compose.material.icons.filled.Nature
import androidx.compose.material.icons.filled.Water
import androidx.compose.ui.graphics.vector.ImageVector

data class Cities(
    val id: Int,
    val title: String,
    val icon: ImageVector
)

fun CitiesRepo(): List<Cities> {
    return listOf(
        Cities(id = 1, title = "Rio de Janeiro", icon = Icons.Filled.BeachAccess),
        Cities(id = 2, title = "São Paulo", icon = Icons.Filled.Museum),
        Cities(id = 3, title = "Florianópolis", icon = Icons.Filled.BeachAccess),
        Cities(id = 4, title = "Manaus", icon = Icons.Filled.Nature),
        Cities(id = 5, title = "Foz do Iguaçu", icon = Icons.Filled.Water),
        Cities(id = 6, title = "Brasília", icon = Icons.Filled.AccountBalance),
    )
}
package br.com.renankoji.pockettravel.repository

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalance
import androidx.compose.material.icons.filled.Hiking
import androidx.compose.material.icons.filled.Landscape
import androidx.compose.material.icons.filled.NaturePeople
import androidx.compose.material.icons.filled.Park
import androidx.compose.material.icons.filled.Tsunami
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import br.com.renankoji.pockettravel.R
import br.com.renankoji.pockettravel.model.DashboardCategoriesList

@Composable
fun getAllDashboardCategoriesListRepo(): List<DashboardCategoriesList> {
    return listOf(
        DashboardCategoriesList(
            id = 0,
            title = stringResource(id = R.string.dashboard_categories_camp),
            icon = Icons.Filled.Landscape
        ),
        DashboardCategoriesList(
            id = 1,
            title = stringResource(id = R.string.dashboard_categories_beach),
            icon = Icons.Filled.Tsunami
        ),
        DashboardCategoriesList(
            id = 2,
            title = stringResource(id = R.string.dashboard_categories_tracking),
            icon = Icons.Filled.Hiking
        ),
        DashboardCategoriesList(
            id = 3,
            title = stringResource(id = R.string.dashboard_categories_museum),
            icon = Icons.Filled.AccountBalance
        ),
        DashboardCategoriesList(
            id = 4,
            title = stringResource(id = R.string.dashboard_categories_park),
            icon = Icons.Filled.Park
        ),
    )
}
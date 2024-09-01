package br.com.renankoji.pockettravel.repository

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import br.com.renankoji.pockettravel.R

data class CityScreenList(
    val id: Long = 0,
    val title: String = "",
    val painter: Painter
)

@Composable
fun CityScreenBackgrounds(): List<CityScreenList> {
    return listOf(
        CityScreenList(id = 0, title = "Rio de Janeiro", painter = painterResource(id = R.drawable.background_riodejaneiro)),
        CityScreenList(id = 1, title = "São Paulo", painter = painterResource(id = R.drawable.background_saopaulo)),
        CityScreenList(id = 2, title = "Florianópolis", painter = painterResource(id = R.drawable.background_florianopolis)),
        CityScreenList(id = 3, title = "Manaus", painter = painterResource(id = R.drawable.background_manaus)),
        CityScreenList(id = 4, title = "Foz do Iguaçu", painter = painterResource(id = R.drawable.background_fozdoiguacu)),
        CityScreenList(id = 5, title = "Brasília", painter = painterResource(id = R.drawable.background_brasilia))
    )
}
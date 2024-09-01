import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import br.com.renankoji.pockettravel.R
import br.com.renankoji.pockettravel.components.ButtonOptionItem
import br.com.renankoji.pockettravel.repository.CategoriesRepo
import br.com.renankoji.pockettravel.repository.CitiesRepo

@Composable
fun FormSearchPlaces(navController: NavController) {
    val cities = CitiesRepo()
    val categories = CategoriesRepo()

    val chosenCity = remember {
        mutableStateOf("")
    }
    val chosenCategory = remember {
        mutableStateOf("")
    }

    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Encontre locais de seu intesse", fontSize = 24.sp, fontWeight = FontWeight.SemiBold)
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Selecione um local", fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
        Spacer(modifier = Modifier.height(16.dp))

        // cities list
        LazyRow(
            contentPadding = PaddingValues(horizontal = 5.dp),
        ) {
            items(cities) { city ->
                ButtonOptionItem(title = city.title,
                    backgroundColor = if (chosenCity.value == city.title) colorResource(id = R.color.button_color) else colorResource(
                        id = R.color.primary_500
                    ),
                    icon = city.icon,
                    borderColor = colorResource(id = R.color.primary_500),
                    shape = RoundedCornerShape(50),
                    onClick = { chosenCity.value = city.title })
            }
        }

        Text(text = "Selecione uma categoria", fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
        Spacer(modifier = Modifier.height(16.dp))

        // categories list
        LazyRow(
            contentPadding = PaddingValues(horizontal = 5.dp)
        ) {
            items(categories) { category ->
                ButtonOptionItem(title = category.title,
                    backgroundColor = if (chosenCategory.value == category.featureClass) colorResource(
                        id = R.color.button_color
                    ) else colorResource(
                        id = R.color.primary_500
                    ),
                    icon = category.icon,
                    borderColor = colorResource(id = R.color.primary_200),
                    shape = RoundedCornerShape(16.dp),
                    onClick = { chosenCategory.value = category.featureClass })
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Box(
            modifier = Modifier.fillMaxWidth(),  // O Box ocupa toda a largura
            contentAlignment = Alignment.Center  // Centraliza o conteúdo dentro do Box
        ) {
            Button(
                onClick = { navController.navigate("city/${chosenCity.value}/${chosenCategory.value}") },
                enabled = chosenCity.value.isNotEmpty() && chosenCategory.value.isNotEmpty(),
                modifier = Modifier
                    .width(200.dp)
                    .height(48.dp),
                shape = RoundedCornerShape(32.dp),
                colors = ButtonDefaults.buttonColors(colorResource(id = R.color.button_color))
            ) {
                Text(
                    "Pesquisar",
                    fontWeight = FontWeight.Bold,
                    color = colorResource(id = R.color.white),
                    fontSize = 22.sp,
                    letterSpacing = 2.sp,
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FormPreview() {
    val navController = rememberNavController()
    FormSearchPlaces(navController)
}
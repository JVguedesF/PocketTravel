package br.com.renankoji.pockettravel.components

import android.widget.RatingBar
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
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
import br.com.renankoji.pockettravel.R

@Composable
fun Places(local: String, rate: Int = 0, event1: String, event2: String) {
    val rating = remember {
        mutableStateOf(rate)
    }
    Column(
        Modifier.fillMaxWidth(),
    ) {
        Row(modifier = Modifier.padding(16.dp), horizontalArrangement = Arrangement.Center) {
            Box(
                modifier = Modifier
                    .size(90.dp)
                    .border(
                        1.dp,
                        colorResource(id = R.color.primary_900),
                        shape = RoundedCornerShape(40.dp)
                    )
                    .clickable { /* Ação ao clicar no item */ },
                contentAlignment = Alignment.Center
            ) {
                Text(text = "Item", color = colorResource(id = R.color.primary_900))
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column(
                verticalArrangement = Arrangement.SpaceBetween, modifier = Modifier.height(94.dp)
            ) {
                Text(text = local, fontWeight = FontWeight.Bold)
                RatingBar(
                    rating = rating.value,
                    onRatingChanged = { newRating -> rating.value = newRating })
                Column {
                    Text(
                        text = "Próximos eventos:",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                    )

                    Text(text = event1, fontSize = 12.sp, color = colorResource(id = R.color.primary_900))
                    Text(text = event2, fontSize = 12.sp, color = colorResource(id = R.color.primary_900))
                }
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun PlacesPreview() {
    Places("Arcos da Lapa", 3, "🌃 Feira noturna (05/09)", "🍔 Feira Gastronomica (07/09)")
}
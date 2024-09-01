package br.com.renankoji.pockettravel.components

import android.icu.text.DecimalFormat
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.renankoji.pockettravel.R

@Composable
fun CardCityInfo(local: String, distance: Double) {
    val format = DecimalFormat("#,###.00")
    val formattedDistance = format.format(distance)

    Card(modifier = Modifier.fillMaxWidth()) {
        Row(modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(), horizontalArrangement = Arrangement.Start) {
            Spacer(modifier = Modifier.width(16.dp))
            Column(
                verticalArrangement = Arrangement.SpaceBetween, modifier = Modifier.height(94.dp)
            ) {
                Text(text = local, fontWeight = FontWeight.Black, fontSize = 20.sp)
                Column {
                    Text(
                        text = "Distância:",
                        fontSize = 18.sp,
                    )

                    Text(
                        text = "$formattedDistance km",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun CardCityInfoPreview() {
    CardCityInfo("Arcos da Lapa", 151515250.51515)
}
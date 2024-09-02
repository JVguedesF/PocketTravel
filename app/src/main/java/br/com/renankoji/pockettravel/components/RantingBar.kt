package br.com.renankoji.pockettravel.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import br.com.renankoji.pockettravel.R

@Composable
fun RatingBar(
    modifier: Modifier = Modifier, rating: Int = 0, onRatingChanged: (Int) -> Unit
) {
    Row(modifier = modifier) {
        for (i in 1..5) {
            Icon(painter = painterResource(id = if (i <= rating) R.drawable.baseline_star_12 else R.drawable.baseline_star_outline_12),
                contentDescription = "Star $i",
                tint = Color.Black,
                modifier = Modifier.clickable { onRatingChanged(i) })
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RatingBarPreview() {
    val rating = remember { mutableStateOf(3) }

    RatingBar(rating = rating.value, onRatingChanged = { newRating -> rating.value = newRating })
}
package br.com.renankoji.pockettravel.components

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.renankoji.pockettravel.R

@Composable
fun ButtonGo(
    modifier: Modifier,
    text: String,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .width(200.dp)
            .height(48.dp),
        shape = RoundedCornerShape(32.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = colorResource(id = R.color.button_color)
        ),
    )
    {
        Text(
            text = text,
            fontWeight = FontWeight.Bold,
            color = colorResource(id = R.color.white),
            fontSize = 22.sp,
            letterSpacing = 2.sp,
            fontFamily = karlaFont
        )
    }
}
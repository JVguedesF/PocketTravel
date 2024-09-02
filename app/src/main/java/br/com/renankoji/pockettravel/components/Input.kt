package br.com.renankoji.pockettravel.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.renankoji.pockettravel.R


val karlaFont = FontFamily(
    Font(R.font.karla_variation_font)
)

@Composable
fun Input(
    modifier: Modifier = Modifier,
    value: String,
    placeholder: String,
    label: String? = null,
    keyboardType: KeyboardType,
    updateInfo: (String) -> Unit = {},
    isError: Boolean = false,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    trailingIcon: @Composable (() -> Unit)? = null,
    icon: ImageVector? = null,
    onIconClick: (() -> Unit)? = null,
    unfocusedBorderColor: Color = Color.Transparent,
    focusedBorderColor: Color = Color.Transparent,
    unfocusedContainerColor: Color = colorResource(id = R.color.primary_200),
    focusedContainerColor: Color = colorResource(id = R.color.primary_200),
    textColor: Color = colorResource(id = R.color.text_color),
    placeholderColor: Color = colorResource(id = R.color.text_color),
    iconColor: Color = colorResource(id = R.color.text_color)
) {
    OutlinedTextField(
        value = value,
        onValueChange = updateInfo,
        modifier = modifier.fillMaxWidth(),
        placeholder = {
            Text(
                text = placeholder,
                fontFamily = karlaFont,
                fontSize = 20.sp,
                color = placeholderColor
            )
        },
        label = label?.let {
            {
                Text(
                    text = it,
                    fontFamily = karlaFont,
                    fontSize = 20.sp,
                    color = textColor
                )
            }
        },
        shape = RoundedCornerShape(8.dp),
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        visualTransformation = visualTransformation,
        trailingIcon = {
            when {
                icon != null -> {
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        tint = iconColor,
                        modifier = Modifier.clickable { onIconClick?.invoke() }
                    )
                }

                trailingIcon != null -> trailingIcon()
            }
        },
        isError = isError,
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedBorderColor = unfocusedBorderColor,
            focusedBorderColor = focusedBorderColor,
            unfocusedContainerColor = unfocusedContainerColor,
            focusedContainerColor = focusedContainerColor,
            focusedTextColor = textColor,
            unfocusedTextColor = textColor
        ),
    )
}
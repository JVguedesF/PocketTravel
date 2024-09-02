package br.com.renankoji.pockettravel.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DisplayMode.Companion.Input
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import br.com.renankoji.pockettravel.R
import br.com.renankoji.pockettravel.components.Input
import br.com.renankoji.pockettravel.components.karlaFont
import br.com.renankoji.pockettravel.components.ButtonGo

@Composable
fun LoginScreen(navController: NavController) {
    var email by remember { mutableStateOf("") }
    var errorEmail by remember { mutableStateOf(false) }

    var password by remember { mutableStateOf("") }
    var errorPassword by remember { mutableStateOf(false) }
    var passwordVisible by remember { mutableStateOf(false) }

    val passwordLeight = 8

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                colorResource(id = R.color.background_color),
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            //header
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "logo",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .size(200.dp)
                        .padding(top = 8.dp)
                )
            }
            //form
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp)

            ) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth(),
                    colors = CardDefaults
                        .cardColors(colorResource(id = R.color.background_color)),
                ) {
                    Column(
                        modifier = Modifier.padding(
                            horizontal = 32.dp
                        )
                    ) {
                        Input(
                            value = email,
                            placeholder = stringResource(id = R.string.login_email_placeholder),
                            label = stringResource(id = R.string.login_email_label),
                            modifier = Modifier,
                            keyboardType = KeyboardType.Email,
                            updateInfo = {
                                email = it
                                if (email.isNotEmpty()) errorEmail = false

                            },
                            isError = errorEmail,

                            )

                        if (errorEmail) {
                            Text(
                                text = stringResource(id = R.string.login_email_message),
                                modifier = Modifier
                                    .fillMaxWidth(),
                                color = colorResource(id = R.color.red),
                                fontSize = 14.sp,
                                textAlign = TextAlign.End,
                                fontFamily = karlaFont
                            )
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        Input(
                            value = password,
                            placeholder = stringResource(id = R.string.login_password_placeholder),
                            label = stringResource(id = R.string.login_password_label),
                            modifier = Modifier,
                            keyboardType = KeyboardType.Password,
                            isError = errorPassword,
                            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                            trailingIcon = {
                                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                                    Icon(
                                        imageVector = if (passwordVisible) Icons.Filled.VisibilityOff else Icons.Filled.Visibility,
                                        contentDescription = if (passwordVisible) "hide password" else "show password"
                                    )
                                }
                            },
                            updateInfo = {
                                if (it.length <= passwordLeight) password = it
                            }
                        )

                        if (errorPassword) {
                            Text(
                                text = stringResource(id = R.string.login_password_message),
                                modifier = Modifier
                                    .fillMaxWidth(),
                                color = colorResource(id = R.color.red),
                                fontSize = 14.sp,
                                textAlign = TextAlign.End,
                                fontFamily = karlaFont
                            )
                        }

                        Spacer(modifier = Modifier.height(6.dp))

                        Text(
                            text = stringResource(id = R.string.login_password_forgot),
                            fontWeight = FontWeight.Normal,
                            color = colorResource(id = R.color.text_color),
                            fontSize = 16.sp,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth(),
                            fontFamily = karlaFont
                        )

                        Spacer(modifier = Modifier.height(48.dp))

                        ButtonGo(
                            text = stringResource(id = R.string.login_button_enter),
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally),
                            onClick = {
                                errorEmail = false
                                errorPassword = false

                                if (email.isEmpty()) {
                                    errorEmail = true
                                }
                                if (password.isEmpty()) {
                                    errorPassword = true
                                }
                                if (!errorEmail && !errorPassword) {
                                    navController.navigate("dashboard")
                                }
                            }
                        )

                        Spacer(modifier = Modifier.height(30.dp))

                        HorizontalDivider(
                            modifier = Modifier.padding(vertical = 8.dp),
                            thickness = 1.dp,
                            color = colorResource(id = R.color.primary_700)
                        )

                        Spacer(modifier = Modifier.height(20.dp))

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp, vertical = 4.dp),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.facebook),
                                contentDescription = "Facebook Logo",
                                modifier = Modifier
                                    .size(35.dp)

                            )
                            Spacer(modifier = Modifier.width(20.dp))

                            Image(
                                painter = painterResource(id = R.drawable.gmail),
                                contentDescription = "Gmail Logo",
                                modifier = Modifier
                                    .size(30.dp)
                            )
                        }

                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp, vertical = 4.dp),

                            ) {
                            Text(
                                text = stringResource(id = R.string.login_subscribe_phrase),
                                fontWeight = FontWeight.Normal,
                                color = colorResource(id = R.color.text_color),
                                fontSize = 16.sp,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.fillMaxWidth(),
                                fontFamily = karlaFont
                            )
                            Text(
                                text = stringResource(id = R.string.login_subscribe_link),
                                fontWeight = FontWeight.Normal,
                                color = colorResource(id = R.color.white),
                                fontSize = 16.sp,
                                textAlign = TextAlign.Center,
                                fontFamily = karlaFont,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable {
                                        navController.navigate("subscribe")
                                    }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun LoginScreenPreview() {
    val navController = rememberNavController()
    LoginScreen(navController)
}
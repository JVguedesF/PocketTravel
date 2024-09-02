package br.com.renankoji.pockettravel.screens

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Image
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.com.fiap.pockettravel.database.repository.UserRepository
import br.com.renankoji.pockettravel.R
import br.com.renankoji.pockettravel.components.ButtonGo
import br.com.renankoji.pockettravel.components.Input
import br.com.renankoji.pockettravel.model.User
import coil.compose.rememberAsyncImagePainter

@Composable
fun SubscribeScreen(
    firstName: String,
    lastName: String,
    email: String,
    navController: NavController
) {
    //context and repository
    val context = LocalContext.current
    val userRepository = UserRepository(context)

    //scroll state
    val scrollState = rememberScrollState()

    //upload image
    var selectedImage by remember { mutableStateOf<Uri?>(null) }
    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? -> selectedImage = uri }

    var firstName by remember { mutableStateOf("") }
    var errorFirstName by remember { mutableStateOf(false) }

    var lastName by remember { mutableStateOf("") }
    var errorLastName by remember { mutableStateOf(false) }

    var email by remember { mutableStateOf("") }
    var errorEmail by remember { mutableStateOf(false) }

    var password by remember { mutableStateOf("") }
    var errorPassword by remember { mutableStateOf(false) }

    var confirmPassword by remember { mutableStateOf("") }
    var errorConfirmPassword by remember { mutableStateOf(false) }
    var passwordMismatch by remember { mutableStateOf(false) }

    var acceptTerms by remember { mutableStateOf(false) }
    var receiveUpdates by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.background_color))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 10.dp, vertical = 10.dp)
                .verticalScroll(scrollState)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                //upload image
                Box(
                    modifier = Modifier
                        .size(70.dp)
                        .border(
                            1.dp,
                            colorResource(id = R.color.primary_900),
                            shape = CircleShape
                        )
                        .clickable { imagePickerLauncher.launch("image/*") },
                    contentAlignment = Alignment.Center
                ) {
                    if (selectedImage != null) {
                        Image(
                            painter = rememberAsyncImagePainter(model = selectedImage),
                            contentDescription = "Profile Image",
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )
                    } else {
                        Icon(
                            imageVector = Icons.Outlined.Image,
                            contentDescription = "Add Photo",
                            tint = colorResource(id = R.color.primary_900),
                            modifier = Modifier.size(50.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(4.dp))
            }
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(colorResource(id = R.color.background_color)),
            ) {
                Column(
                    modifier = Modifier.padding(horizontal = 32.dp)
                ) {
                    Input(
                        value = firstName,
                        placeholder = stringResource(id = R.string.subscribe_firstName_placeholder),
                        label = stringResource(id = R.string.subscribe_firstName_label),
                        keyboardType = KeyboardType.Text,
                        updateInfo = {
                            firstName = it
                            errorFirstName = it.isEmpty()
                        },
                        isError = errorFirstName
                    )

                    if (errorFirstName) {
                        Text(
                            text = stringResource(id = R.string.subscribe_firstName_message),
                            modifier = Modifier.fillMaxWidth(),
                            color = colorResource(id = R.color.red),
                            fontSize = 14.sp,
                            textAlign = TextAlign.End
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Input(
                        value = lastName,
                        placeholder = stringResource(id = R.string.subscribe_lastName_placeholder),
                        label = stringResource(id = R.string.subscribe_lastName_label),
                        keyboardType = KeyboardType.Text,
                        updateInfo = {
                            lastName = it
                            errorLastName = it.isEmpty()
                        },
                        isError = errorLastName
                    )

                    if (errorLastName) {
                        Text(
                            text = stringResource(id = R.string.subscribe_lastName_message),
                            modifier = Modifier.fillMaxWidth(),
                            color = colorResource(id = R.color.red),
                            fontSize = 14.sp,
                            textAlign = TextAlign.End
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Input(
                        value = email,
                        placeholder = stringResource(id = R.string.subscribe_email_placeholder),
                        label = stringResource(id = R.string.subscribe_email_label),
                        keyboardType = KeyboardType.Email,
                        updateInfo = {
                            email = it
                        },
                        isError = errorEmail
                    )

                    if (errorEmail) {
                        Text(
                            text = stringResource(id = R.string.subscribe_email_error),
                            modifier = Modifier.fillMaxWidth(),
                            color = colorResource(id = R.color.red),
                            fontSize = 14.sp,
                            textAlign = TextAlign.End
                        )
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    Input(
                        value = password,
                        placeholder = stringResource(id = R.string.subscribe_password_placeholder),
                        label = stringResource(id = R.string.subscribe_password_label),
                        keyboardType = KeyboardType.Password,
                        visualTransformation = PasswordVisualTransformation(),
                        updateInfo = {
                            password = it
                            errorPassword = it.isEmpty()
                        },
                        isError = errorPassword
                    )

                    if (errorPassword) {
                        Text(
                            text = stringResource(id = R.string.subscribe_password_message_error),
                            modifier = Modifier.fillMaxWidth(),
                            color = colorResource(id = R.color.red),
                            fontSize = 14.sp,
                            textAlign = TextAlign.End
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Input(
                        value = confirmPassword,
                        placeholder = stringResource(id = R.string.subscribe_confirmPassword_placeholder),
                        label = stringResource(id = R.string.subscribe_confirmPassword_label),
                        keyboardType = KeyboardType.Password,
                        visualTransformation = PasswordVisualTransformation(),
                        updateInfo = {
                            confirmPassword = it
                            errorConfirmPassword = it.isEmpty()
                        },
                        isError = errorConfirmPassword
                    )

                    if (errorConfirmPassword) {
                        Text(
                            text = stringResource(id = R.string.subscribe_confirmPassword_message_error),
                            modifier = Modifier.fillMaxWidth(),
                            color = colorResource(id = R.color.red),
                            fontSize = 14.sp,
                            textAlign = TextAlign.End
                        )
                    }

                    if (passwordMismatch) {
                        Text(
                            text = stringResource(id = R.string.subscribe_password_mismatch_message),
                            modifier = Modifier.fillMaxWidth(),
                            color = colorResource(id = R.color.red),
                            fontSize = 14.sp,
                            textAlign = TextAlign.End
                        )
                    }

                    //checkboxes
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 2.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Checkbox(
                            checked = receiveUpdates,
                            onCheckedChange = { receiveUpdates = it },
                            colors = CheckboxDefaults.colors(
                                checkedColor = colorResource(id = R.color.button_color),
                                uncheckedColor = colorResource(id = R.color.white),
                                checkmarkColor = colorResource(id = R.color.white)
                            )
                        )
                        Text(
                            text = stringResource(id = R.string.subscribe_checkbox_receive_updates),
                            modifier = Modifier.padding(start = 8.dp),
                            color = colorResource(id = R.color.text_color),
                            fontSize = 13.sp
                        )
                    }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 2.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Checkbox(
                            checked = acceptTerms,
                            onCheckedChange = { acceptTerms = it },
                            colors = CheckboxDefaults.colors(
                                checkedColor = colorResource(id = R.color.button_color),
                                uncheckedColor = colorResource(id = R.color.white),
                                checkmarkColor = colorResource(id = R.color.white)
                            )
                        )
                        Text(
                            text = buildAnnotatedString {
                                withStyle(
                                    style = SpanStyle(
                                        color = colorResource(id = R.color.text_color),
                                        fontSize = 13.sp
                                    )
                                ) {
                                    append(stringResource(id = R.string.subscribe_checkbox_accept_terms_part1))
                                }
                                append(" ")
                                withStyle(
                                    style = SpanStyle(
                                        color = colorResource(id = R.color.white),
                                        fontSize = 13.sp,
                                    )
                                ) {
                                    append(stringResource(id = R.string.terms_of_service))
                                }
                                append(" ")
                                withStyle(
                                    style = SpanStyle(
                                        color = colorResource(id = R.color.text_color),
                                        fontSize = 13.sp
                                    )
                                ) {
                                    append(stringResource(id = R.string.subscribe_checkbox_accept_terms_part2))
                                }
                                append(" ")
                                withStyle(
                                    style = SpanStyle(
                                        color = colorResource(id = R.color.white),
                                        fontSize = 13.sp,
                                    )
                                ) {
                                    append(stringResource(id = R.string.privacy_policy))
                                }
                                append(" ")
                                withStyle(
                                    style = SpanStyle(
                                        color = colorResource(id = R.color.text_color),
                                        fontSize = 13.sp
                                    )
                                ) {
                                    append(stringResource(id = R.string.subscribe_checkbox_accept_terms_part3))
                                }
                            },
                            modifier = Modifier.padding(start = 8.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    ButtonGo(
                        text = stringResource(id = R.string.subscribe_button_enter),
                        modifier = Modifier.align(Alignment.CenterHorizontally),
                        onClick = {
                            errorFirstName = firstName.isEmpty()
                            errorLastName = lastName.isEmpty()
                            errorEmail = email.isEmpty()
                            errorPassword = password.isEmpty()
                            errorConfirmPassword = confirmPassword.isEmpty()
                            passwordMismatch =
                                password != confirmPassword && !errorPassword && !errorConfirmPassword

                            if (!errorFirstName && !errorLastName && !errorEmail && !errorPassword && !errorConfirmPassword && !passwordMismatch) {
                                val user = User(
                                    idUser = 0,
                                    firstName = firstName,
                                    lastName = lastName,
                                    email = email
                                )
                                userRepository.saveUser(user)
                                navController.navigate("dashboard")
                            }
                        }
                    )
                }
            }
        }
    }
}

package com.example.mathapp.ui.Screen.signUp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.mathapp.R

@Composable
fun SignUpScreen(
    openAndPopUp: (String,String) -> Unit,
    modifier: Modifier = Modifier,
    navController: NavHostController,
    viewModel: SignUpViewModel = hiltViewModel()
) {
    var email = viewModel.email.collectAsState()
    var password = viewModel.password.collectAsState()
    var username = viewModel.username.collectAsState()
    var confirmPassword = viewModel.confirmPassword.collectAsState()


    Scaffold (
        modifier = Modifier.fillMaxSize(),
        topBar = {
            IconButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentSize(align = Alignment.CenterStart)
                    .padding(vertical = 16.dp),
                onClick = {}
            ) {
                Icon(modifier = Modifier.size(32.dp),
                    imageVector = Icons.Default.KeyboardArrowLeft,
                    contentDescription = "Back Arrow")
            }
        }
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            verticalArrangement = Arrangement.Top
        ) {

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentSize()
                    .padding(vertical = 24.dp, horizontal = 16.dp),
                text = "Hello! Register to get Started",
                fontSize = 32.sp,
                lineHeight = 32.sp,
                color = Color.Black,
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Start
            )
            Spacer(modifier = Modifier.height(16.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {


                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp, vertical = 4.dp),
                    value = username.value,
                    onValueChange = { viewModel.updateUsername(it) },
                    label = { Text(text = "Username") },
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text),
                    shape = RoundedCornerShape(12.dp)
                )

                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp, vertical = 4.dp),
                    value = email.value,
                    onValueChange = { viewModel.updateEmail(it) },
                    label = { Text(text = "Email") },
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Email),
                    shape = RoundedCornerShape(12.dp)
                )

                var passwordVisibility by remember { mutableStateOf(false) }

                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp, vertical = 4.dp),
                    value = password.value,
                    onValueChange = { viewModel.updatePassword(it) },
                    label = { Text(text = "Password") },
                    visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Password),
                    trailingIcon = {
                        IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
                            Icon(
                                painter = painterResource(
                                    if (passwordVisibility) R.drawable.visibility_off else R.drawable.visibility
                                ),
                                contentDescription = null
                            )
                        }

                    },
                    shape = RoundedCornerShape(12.dp)
                )
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp, vertical = 4.dp),
                    value = confirmPassword.value,
                    onValueChange = { viewModel.updateConfirmPassword(it) },
                    label = { Text(text = "Confirm password") },
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Password),
                    trailingIcon = {
                        IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
                            Icon(
                                painter = painterResource(
                                    if (passwordVisibility) R.drawable.visibility_off else R.drawable.visibility
                                ),
                                contentDescription = null
                            )
                        }
                    },
                    shape = RoundedCornerShape(12.dp)
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Register Button
                Button(
                    onClick = {viewModel.onSignUpClick(openAndPopUp) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    colors = ButtonDefaults.buttonColors(Color.Black)
                ) {
                    Text(text = "Register")
                }


                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                ) {
                    Divider(
                        modifier = Modifier
                            .weight(1f) // Occupy space before text
                            .align(Alignment.CenterVertically)
                    )
                    Text(
                        text = "Or Login with",
                        modifier = Modifier
                            .padding(horizontal = 8.dp)
                            .align(Alignment.CenterVertically)
                    )
                    Divider(
                        modifier = Modifier
                            .weight(1f) // Occupy space after text
                            .align(Alignment.CenterVertically)
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    IconButton(onClick = {}) {
                        Icon(
                            modifier = Modifier
                                .size(36.dp)
                                .padding(end = 8.dp),
                            painter = painterResource(R.drawable.facebook),
                            contentDescription = "FaceBook_logo",
                            tint = Color.Unspecified
                        )
                    }

                    IconButton(onClick = {}) {
                        Icon(
                            modifier = Modifier
                                .size(48.dp)
                                .padding(8.dp),
                            painter = painterResource(R.drawable.google),
                            contentDescription = "FaceBook_logo",
                            tint = Color.Unspecified
                        )
                    }
                    IconButton(onClick = {}) {
                        Icon(
                            modifier = Modifier
                                .size(48.dp)
                                .padding(8.dp)
                                .fillMaxWidth(),
                            painter = painterResource(R.drawable.apple),
                            contentDescription = "FaceBook_logo",
                            tint = Color.Unspecified
                        )
                    }
                }

            }
        }
        Row (modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.Bottom
        ){
            Text(modifier = Modifier.padding(vertical = 12.dp),
                text = "Already have a account?")
            TextButton(onClick = {
                navController.navigate("LogInScreen")
            },
                modifier = Modifier.padding()
            ) {
                Text(modifier = Modifier
                    .wrapContentSize()
                    .padding(top = 4.dp),
                    textAlign = TextAlign.Center,
                    text = "Login Now")
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
private fun SignUpPreview() {
    val navController: NavHostController = NavHostController(LocalContext.current)
//    SignUpScreen(modifier = Modifier,navController)
}
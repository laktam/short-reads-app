package org.mql.laktam.shortreads.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import org.mql.laktam.shortreads.models.AuthState
import org.mql.laktam.shortreads.viewmodels.AuthViewModel

@Composable
fun SignupScreen(viewModel: AuthViewModel,  navController: NavController ) {
    LaunchedEffect(Unit) {
        viewModel.resetState()
    }


    val authState by viewModel.authState.observeAsState()
    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    // Check for success state and navigate to login screen
    LaunchedEffect(authState) {
        if (authState is AuthState.Success) {
            navController.navigate("login")
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        when (authState) {
            is AuthState.Loading -> {
                CircularProgressIndicator()
            }
            else -> {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Spacer(modifier = Modifier.height(30.dp))

                    TextField(
                        value = username,
                        onValueChange = { username = it },
                        label = { Text("Username") },
                        modifier = Modifier.fillMaxWidth()
                    )

                    TextField(
                        value = email,
                        onValueChange = { email = it },
                        label = { Text("Email") },
                        modifier = Modifier.fillMaxWidth()
                    )

                    TextField(
                        value = password,
                        onValueChange = { password = it },
                        label = { Text("Password") },
                        modifier = Modifier.fillMaxWidth(),
                        visualTransformation = PasswordVisualTransformation()
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Button(onClick = {
                        viewModel.signup(username, email, password)
                    }, modifier = Modifier.fillMaxWidth()) {
                        Text("Sign Up")
                    }

                    when (authState) {
                        is AuthState.Success -> {
                            val message = (authState as AuthState.Success).message
                            Text(text = message)
                        }
                        is AuthState.Error -> {
                            val message = (authState as AuthState.Error).message
                            Text(text = message, color = MaterialTheme.colorScheme.error)
                        }
                        else -> {}
                    }
                }
            }
        }
    }
}


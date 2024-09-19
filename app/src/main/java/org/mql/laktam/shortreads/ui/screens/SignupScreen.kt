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
import org.mql.laktam.shortreads.models.AuthState
import org.mql.laktam.shortreads.viewmodel.AuthViewModel

@Composable
fun SignupScreen(viewModel: AuthViewModel) {
    val authState by viewModel.authState.observeAsState()
    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        when (authState) {
            is AuthState.Loading -> {
                // Show CircularProgressIndicator in the center of the screen
                CircularProgressIndicator()
            }
            else -> {
                // Show the form if the state is not loading
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
                            val user = (authState as AuthState.Success).user
                            Text(text = "Welcome, ${user.username}")
                        }

                        is AuthState.Error -> {
                            val message = (authState as AuthState.Error).message
                            Text(text = message, color = MaterialTheme.colorScheme.error)
                        }

                        null -> {
                            // No state
                        }

                        else -> {}
                    }
                }
            }
        }
    }
}


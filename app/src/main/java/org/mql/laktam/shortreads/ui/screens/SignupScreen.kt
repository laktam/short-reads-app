package org.mql.laktam.shortreads.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import org.mql.laktam.shortreads.models.AuthState
import org.mql.laktam.shortreads.viewmodel.AuthViewModel

@Composable
fun SignupScreen(viewModel: AuthViewModel) {
    val authState by viewModel.authState.observeAsState()
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
        when (authState) {
            is AuthState.Loading -> {
                CircularProgressIndicator(modifier = Modifier.fillMaxWidth())
            }
            is AuthState.Success -> {
                val user = (authState as AuthState.Success).user
                Text(text = "Welcome, ${user.username}")
            }
            is AuthState.Error -> {
                val message = (authState as AuthState.Error).message
                Text(text = "Error: $message", color = MaterialTheme.colorScheme.error)
            }
            null -> {
                Text(text = "No state")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Name") },
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
            viewModel.signup(name, email, password)
        }, modifier = Modifier.fillMaxWidth()) {
            Text("Sign Up")
        }
    }
}

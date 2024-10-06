package org.mql.laktam.shortreads.ui.screens

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import org.mql.laktam.shortreads.viewmodels.PostsViewModel

@Composable
fun NewPostScreen(navController: NavController, postsViewModel: PostsViewModel, username: String) {
    var content by remember { mutableStateOf("") }
    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }
    val context = LocalContext.current
    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let {
            selectedImageUri = it
            postsViewModel.onImageSelected(it, context)
        }
    }

    Column (
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween, // Space between elements
        horizontalAlignment = Alignment.CenterHorizontally // Center horizontally
    ) {
        // Centered TextField
        Box(
            modifier = Modifier
                .weight(1f), // Takes up remaining space
            contentAlignment = Alignment.Center // Align content in the center
        ) {
            TextField(
                value = content,
                onValueChange = { content = it },
                modifier = Modifier
                    .fillMaxWidth(0.8f), // Adjust width as needed
                placeholder = { Text("Enter text") },
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color.Transparent, // No background
                    focusedContainerColor = Color.Transparent,   // No background
                    focusedIndicatorColor = Color.Transparent,   // No underline when focused
                    unfocusedIndicatorColor = Color.Transparent  // No underline when unfocused
                )
            )
        }

        Button(
            onClick = {
                imagePickerLauncher.launch("image/*")
            },
            modifier = Modifier
                .padding(bottom = 16.dp) // Add padding from the bottom
//                .fillMaxWidth(0.8f) // Adjust width as needed
        ) {
            Text("Add background image")
        }

        Button(
            onClick = {
                postsViewModel.newPost(username, content){
                    username ->
                    navController.navigate("profile/$username")
                }
            },
            modifier = Modifier
                .padding(bottom = 16.dp) // Add padding from the bottom
//                .fillMaxWidth(0.8f) // Adjust width as needed
        ) {
            Text("Publish post")
        }
    }
}
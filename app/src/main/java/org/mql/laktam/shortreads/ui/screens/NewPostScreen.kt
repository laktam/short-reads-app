package org.mql.laktam.shortreads.ui.screens

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.github.skydoves.colorpicker.compose.ColorEnvelope
import com.github.skydoves.colorpicker.compose.HsvColorPicker
import com.github.skydoves.colorpicker.compose.rememberColorPickerController
import org.mql.laktam.shortreads.ui.components.ColorPicker
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
    // color
    var selectedColor by remember { mutableStateOf(Color.Gray) }
    var showColorPicker by remember { mutableStateOf(false) }

    Column (
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween, // Space between elements
        horizontalAlignment = Alignment.CenterHorizontally // Center horizontally
    ) {
        // Centered TextField
        Box(
            modifier = Modifier
                .weight(1f), // Takes up remaining space
//            contentAlignment = Alignment.Center // Align content in the center
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .aspectRatio(9f / 16f),
                contentAlignment = Alignment.Center,

            ){
                Image(
                    painter = rememberAsyncImagePainter(selectedImageUri),
                    contentDescription = "Background image",
                    modifier = Modifier
                        .fillMaxSize()
                        .aspectRatio(9f / 16f),
                    contentScale = ContentScale.Crop,
                )
                // color overlay
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(selectedColor) // Adjust the color and transparency here
                )

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
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp)
                    .background(Color.Transparent)
//                contentAlignment = Alignment.,
            ){
                Box(
                    modifier = Modifier
                        .size(65.dp) // Adjust size as needed
                        .background(selectedColor, CircleShape)
                        .align(Alignment.BottomEnd)
                        .border(3.dp, Color.Gray, CircleShape)
                        .clickable {
                            showColorPicker = !showColorPicker
                        }
                )
            }
        }
        if (showColorPicker) {
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                ColorPicker(
                    initialColor = selectedColor,
                    onColorChanged = { colorEnvelope: ColorEnvelope ->
                        selectedColor = colorEnvelope.color
                        println("selected color :::: $selectedColor")
                    },
                    onButtonClick = {
                        showColorPicker = !showColorPicker
                    }

                )
            }
        }
        Row (
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(25.dp),
            ){
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
                Text("Publish")
            }
        }

    }
}
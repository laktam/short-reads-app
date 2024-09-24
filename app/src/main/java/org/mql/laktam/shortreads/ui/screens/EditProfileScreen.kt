package org.mql.laktam.shortreads.ui.screens

import android.app.Activity
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import org.mql.laktam.shortreads.auth.TokenManager
import org.mql.laktam.shortreads.models.User
import org.mql.laktam.shortreads.viewmodels.ProfileViewModel

// need to check if current profile is the logged in profile to
// be able to add description or edit profile
@Composable
fun EditProfileScreen(profileViewModel: ProfileViewModel, navController: NavController) {

    val user by profileViewModel.user
    var username by remember { mutableStateOf(user?.username ?: "") }
    var email by remember { mutableStateOf(user?.email ?: "") }
    var description by remember { mutableStateOf(user?.description ?: "") }
    val oldUsername = user?.username?:"";
    val context = LocalContext.current as Activity
    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let {
            profileViewModel.onImageSelected(uri, context)
        }
    }

    val currentUsername by profileViewModel.currentUsername

    user?.let {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            contentAlignment = Alignment.TopCenter
        ) {

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                Spacer(modifier = Modifier.height(35.dp))

                Text(
                    text = "Edit Profile",
//                    style = MaterialTheme.typography.h4,
                    modifier = Modifier.padding(16.dp)
                )

                Image(
                    painter = rememberAsyncImagePainter(it.profilePictureUrl),
                    contentDescription = "Profile Picture",
                    modifier = Modifier
                        .size(128.dp)
                        .padding(8.dp)
                        .clip(CircleShape)
                        .border(2.dp, Color.LightGray, CircleShape)
                        .clickable {
                            imagePickerLauncher.launch("image/*")  // Open file picker when clicked
                        },
                    contentScale = ContentScale.Crop
                )
//                Image(
//                    painter = rememberAsyncImagePainter(it.profilePictureUrl),
//                    contentDescription = "Profile Picture",
//                    modifier = Modifier
//                        .size(128.dp)
//                        .padding(8.dp)
//                        .clip(CircleShape)
//                        .border(2.dp, Color.LightGray, CircleShape)
//                        .clickable {
//                            // Open file picker to select new image
//                            profilePicture = selectImage(context)  // Assume this method gets an image from file picker
//                        },
//                    contentScale = ContentScale.Crop
//                )

                Spacer(modifier = Modifier.height(16.dp))

                Row (
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    // Username TextField
                    OutlinedTextField(
                        value = username,
                        onValueChange = { username = it },
                        label = { Text("Username") },
                        modifier = Modifier.fillMaxWidth()
                    )

                    val followersText = if (it.followersCount == 1) {
                        "Follower"
                    } else {
                        "Followers"
                    }
                    Text(
                        text = "${it.followersCount} $followersText",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.Gray
                    )

                }

           Spacer(modifier = Modifier.height(16.dp))

                // Email TextField
                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("Email") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = description,
                    onValueChange = { description = it },
                    label = { Text("Description") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(24.dp))

                // Save Button
                Button (
                    onClick = {
//                        profileViewModel.updateUserProfile(oldUsername, User(username, email, description)) {
////                            profileViewModel.loadUser(username)
//                            newUsername ->
//                            println("newUsername $newUsername")
//                            navController.navigate("profile/$newUsername")
//                        }
                        profileViewModel.updateUserProfile(oldUsername, User(username, email, description)) {
                            newUsername ->
                            navController.navigate("profile/$newUsername") {
                                popUpTo("profile/$oldUsername") { inclusive = true }
                            }
                        }
                              },
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                ) {
                    Text(text = "Save Changes")
                }

            }
        }
    }

//    user?.let {
//        Box(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(16.dp),
//            contentAlignment = Alignment.TopCenter
//        ) {
//
//            Column(
//                horizontalAlignment = Alignment.CenterHorizontally,
//                modifier = Modifier.fillMaxWidth()
//            ) {
//                Spacer(modifier = Modifier.height(35.dp))
//                Text(text = "Edit Profile",
////                    style = MaterialTheme.typography.h4
//                )
//                Image(
//                    painter = rememberAsyncImagePainter(it.profilePictureUrl),
//                    contentDescription = "Profile Picture",
//                    modifier = Modifier
//                        .size(128.dp)
//                        .padding(8.dp)
//                        .clip(CircleShape)
//                        .border(2.dp, Color.LightGray, CircleShape),
//                    contentScale = ContentScale.Crop
//                )
//
//                Spacer(modifier = Modifier.height(16.dp))
//
//                Row (
//                    verticalAlignment = Alignment.CenterVertically,
//                    horizontalArrangement = Arrangement.Center
//                ) {
//                    Text(
//                        text = it.username,
//                        fontSize = 24.sp,
//                        fontWeight = FontWeight.Bold,
//                        modifier = Modifier.padding(end = 8.dp)
//                    )
//                    val followersText = if(it.followersCount == 1){
//                        "Follower"
//                    }else{
//                        "Followers"
//                    }
//                    Text(
//                        text = "${it.followersCount} $followersText",
//                        fontSize = 18.sp,
//                        fontWeight = FontWeight.Medium,
//                        color = Color.Gray
//                    )
//                }
//
//                Spacer(modifier = Modifier.height(10.dp))
//
//                Text(
//                    text = it.email,
//                    fontSize = 18.sp,
//                    fontWeight = FontWeight.Medium
//                )
//
//                Spacer(modifier = Modifier.height(5.dp))
//
//                Text(
//                    text = it.description,
//                    fontSize = 16.sp,
//                    modifier = Modifier.padding(8.dp)
//                )
//            }
//
//        }
//    }

}

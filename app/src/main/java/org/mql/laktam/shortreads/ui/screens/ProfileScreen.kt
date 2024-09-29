package org.mql.laktam.shortreads.ui.screens

import ProfileHeader
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
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
import org.mql.laktam.shortreads.viewmodels.ProfileViewModel

// need to check if current profile is the logged in profile to
// be able to add description or edit profile
@Composable
fun ProfileScreen(username: String, profileViewModel: ProfileViewModel, navController: NavController) {
    val user by profileViewModel.user
    val currentUsername by profileViewModel.currentUsername
    val followingCurrentProfile by profileViewModel.followingCurrentProfile
    LaunchedEffect(username) {
        profileViewModel.loadUser(username)
        profileViewModel.followingCurrentProfile(username)
    }

    user?.let {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF0052CC)) // Blue background
//                .height(250.dp)
                .padding(30.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ProfileHeader(it)
        }
//        Box(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(16.dp),
//            contentAlignment = Alignment.TopCenter
//        ) {
//            Column(
//                modifier = Modifier.fillMaxWidth()
//            ) {
//                Spacer(modifier = Modifier.height(30.dp))
//                Row (
//                    verticalAlignment = Alignment.CenterVertically,
//                    horizontalArrangement = Arrangement.Center
//                ) {
//                    Spacer(modifier = Modifier.height(35.dp))
//                    Image(
//                        painter = rememberAsyncImagePainter("http://10.0.2.2/short-reads-backend/${it.profilePictureUrl}"),
//                        contentDescription = "Profile Picture",
//                        modifier = Modifier
//                            .size(128.dp)
////                            .padding(8.dp)
//                            .clip(RoundedCornerShape(9.dp))
//                            .border(2.dp, Color.LightGray, RoundedCornerShape(9.dp)), // Same shape for the border
//                        contentScale = ContentScale.Crop
//                    )
//                }
//
//                    // username, follow or edit button
//                    Row (
//                            verticalAlignment = Alignment.CenterVertically,
//                        horizontalArrangement = Arrangement.SpaceBetween,
//                        modifier = Modifier.fillMaxWidth()
//                    ) {
//                        Text(
//                            text = it.username,
//                            fontSize = 24.sp,
//                            fontWeight = FontWeight.Bold,
//                        )
//
//                        if(currentUsername == user?.username){
//                            Button(
//                                onClick = {
//                                    navController.navigate("editProfile")
//                                },
//                            ) {
//                                Icon(
//                                    imageVector = Icons.Default.Edit,
//                                    contentDescription = "Edit Profile",
//                                    modifier = Modifier
//                                        .size(16.dp)
//                                )
//                            }
//                        }else{
//                            // if not followed display follow button or "following"
//                            if(followingCurrentProfile){
//                                Button(
//                                    onClick = {
//                                        profileViewModel.unfollow(it.username)
//                                    },
//                                ) {
//                                    Text("following")
//                                }
//                            }else{
//                                Button(
//                                    onClick = {
//                                        profileViewModel.follow(it.username)
//                                    },
//                                ) {
//                                    Text("follow")
//                                }
//                            }
//
//                        }
//
//                    }
//
//                val followersText = if(it.followersCount == 1){
//                    "Follower"
//                }else{
//                    "Followers"
//                }
//                Text(
//                    text = "${it.followersCount} $followersText",
//                    fontSize = 18.sp,
//                    fontWeight = FontWeight.Medium,
//                    color = Color.Gray,
//                )
//
//                Spacer(modifier = Modifier.height(5.dp))
//                // email
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
////                    modifier = Modifier.padding(8.dp)
//                )
//
//            }
//
//        }
    }

}

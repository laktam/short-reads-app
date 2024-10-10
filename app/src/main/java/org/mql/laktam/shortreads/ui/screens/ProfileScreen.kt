package org.mql.laktam.shortreads.ui.screens

import ProfileHeader
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import org.mql.laktam.shortreads.ui.components.BottomNavigationBar
import org.mql.laktam.shortreads.ui.components.LastPosts
import org.mql.laktam.shortreads.ui.components.TextPostItem
import org.mql.laktam.shortreads.viewmodels.PostsViewModel
import org.mql.laktam.shortreads.viewmodels.ProfileViewModel
import androidx.compose.foundation.lazy.items
// need to check if current profile is the logged in profile to
// be able to add description or edit profile
@Composable
fun ProfileScreen(username: String, profileViewModel: ProfileViewModel, navController: NavController, postsViewModel: PostsViewModel) {
    val user by profileViewModel.user
    val currentUsername by profileViewModel.currentUsername
    val followingCurrentProfile by profileViewModel.followingCurrentProfile
//    val scrollState = rememberScrollState()
    val posts by postsViewModel.posts.collectAsState()
    val isLoading by postsViewModel.isLoading.collectAsState()
    val error by postsViewModel.error.collectAsState()

    LaunchedEffect(username) {
        profileViewModel.loadUser(username)
        profileViewModel.followingCurrentProfile(username)
        postsViewModel.loadPosts(username)

    }

    user?.let {
        Box(modifier = Modifier.fillMaxSize()) {
            Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(290.dp)
                        .background(
                            Color(0xFF0052CC),
                            shape = RoundedCornerShape(bottomEnd = 25.dp)
                        )
                )
            LazyColumn (
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(bottom = 56.dp) // Adjust padding for bottom navigation
            ) {
                // Profile header section
                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
//                            .background(
//                                Color(0xFF0052CC),
//                                shape = RoundedCornerShape(bottomEnd = 25.dp)
//                            )
                            .padding(30.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        // Profile header content
                        Row(
                            modifier = Modifier
                                .padding(top = 25.dp, bottom = 25.dp)
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "Profile",
                                color = Color.White,
                                fontSize = 21.sp,
                                fontWeight = FontWeight.Medium
                            )

                            if (currentUsername == it.username) {
                                Icon(
                                    imageVector = Icons.Default.Edit,
                                    contentDescription = "Edit Profile",
                                    modifier = Modifier
                                        .size(23.dp)
                                        .clickable {
                                            navController.navigate("editProfile")
                                        },
                                    tint = Color.White
                                )
                            }
                        }

                        ProfileHeader(it)
                        LastPosts(it, postsViewModel)
                    }
                }

                items(posts) { post ->
                    TextPostItem(post)
                }

                item {
                    if (isLoading) {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                                .wrapContentSize(Alignment.Center)
                        )
                    }
                }

                // Detect when we've reached the bottom of the list
                item {
                    LaunchedEffect(Unit) {
                        postsViewModel.loadPosts(username)
                    }
                }

            }
            if (error != null) {
                Text(
                    text = error!!,
                    color = Color.Red,
                    modifier = Modifier.padding(16.dp)
                )
            }

            // Bottom navigation bar
            BottomNavigationBar(
                it,
                profileViewModel,
                followingCurrentProfile,
                currentUsername == it.username,
                navController
            )
        }
    }

//    user?.let {
//        Box (modifier = Modifier.fillMaxSize())
//        {
//            Box (
//                modifier = Modifier
//                .fillMaxSize()
//                .verticalScroll(scrollState)
//            ) {
//                Box(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .height(290.dp)
//                        .background(
//                            Color(0xFF0052CC),
//                            shape = RoundedCornerShape(bottomEnd = 25.dp)
//                        )
//                )
//                Column(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(30.dp),
//                    horizontalAlignment = Alignment.CenterHorizontally
//                ) {
//                    // tob bar (Profile , Edit icon)
//                    Row(
//                        modifier = Modifier
//                            .padding(top = 25.dp)
//                            .padding(bottom = 25.dp)
//                            .fillMaxWidth(),
//                        horizontalArrangement = Arrangement.SpaceBetween,
//
//                        ) {
//                        Text(
//                            text = "Profile",
//                            color = Color.White,
//                            fontSize = 21.sp,
//                            fontWeight = FontWeight.Medium,
//                        )
//
//                        if (currentUsername == it.username) {
//                            Icon(
//                                imageVector = Icons.Default.Edit,
//                                contentDescription = "Edit Profile",
//                                modifier = Modifier
//                                    .size(23.dp)
//                                    .clickable {
//                                        navController.navigate("editProfile")
//                                    },
//                                tint = Color.White,
//
//                                )
//                        }
//                    }
//                    ProfileHeader(it)// profile card
//                    Spacer(modifier = Modifier.height(15.dp))
//                    LastPosts(it, postsViewModel)
//                    AllPostsList(postsViewModel, username)
//                }
//            }
//            BottomNavigationBar(it,profileViewModel, followingCurrentProfile, currentUsername == it.username, navController)
//        }
//    }

}

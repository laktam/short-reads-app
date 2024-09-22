package org.mql.laktam.shortreads.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import org.mql.laktam.shortreads.viewmodels.AuthViewModel
import org.mql.laktam.shortreads.viewmodels.ProfileViewModel

// need to check if current profile is the logged in profile to
// be able to add description or edit profile
@Composable
fun ProfileScreen(username: String, navController: NavController) {
    println(username)
    val viewModel = remember { ProfileViewModel() }
    val user by viewModel.user
    val context = LocalContext.current
    LaunchedEffect(username) {
        viewModel.loadUser(username, context)
    }

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
                Image(
                    painter = rememberAsyncImagePainter(it.profilePictureUrl),
                    contentDescription = "Profile Picture",
                    modifier = Modifier
                        .size(128.dp)
                        .padding(8.dp),
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = it.username,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "${it.followersCount} Followers",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = it.email,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Description
                Text(
                    text = it.description,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(8.dp)
                )
            }

        }
    }

//    user?.let {
//        Text("Username: ${it.username}")
//
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(16.dp),
//            horizontalAlignment = Alignment.CenterHorizontally
//        ) {
////        Image(
////            painter = rememberImagePainter(user.profilePictureUrl),
////            contentDescription = null,
////            modifier = Modifier
////                .size(100.dp)
////                .clip(CircleShape)
////                .background(Color.Gray)
////        )
//
//            Spacer(modifier = Modifier.height(16.dp))
//
//            Text(
//                text = it.username,
//            )
//
//            Text(
//                text = "Followers",
//            )
//
//            Spacer(modifier = Modifier.height(8.dp))
//
//        Text(
//            text = it.description,
////            style = MaterialTheme.typography.,
//            textAlign = TextAlign.Center
//        )
//        }
//    }


}

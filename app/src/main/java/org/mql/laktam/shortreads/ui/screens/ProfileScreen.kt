package org.mql.laktam.shortreads.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import org.mql.laktam.shortreads.viewmodels.AuthViewModel
import org.mql.laktam.shortreads.viewmodels.ProfileViewModel

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
        Text("Username: ${it.username}")

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
//        Image(
//            painter = rememberImagePainter(user.profilePictureUrl),
//            contentDescription = null,
//            modifier = Modifier
//                .size(100.dp)
//                .clip(CircleShape)
//                .background(Color.Gray)
//        )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = it.username,
            )

            Text(
                text = "Followers",
            )

            Spacer(modifier = Modifier.height(8.dp))

//        Text(
//            text = it.description,
////            style = MaterialTheme.typography.,
//            textAlign = TextAlign.Center
//        )
        }
    }


}

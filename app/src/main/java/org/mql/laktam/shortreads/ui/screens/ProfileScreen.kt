package org.mql.laktam.shortreads.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import org.mql.laktam.shortreads.models.AuthState
import org.mql.laktam.shortreads.viewmodel.AuthViewModel

@Composable
fun ProfileScreen(username: String, viewModel: AuthViewModel, navController: NavController) {

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
            text = username,
        )

        Text(
            text = "50 Followers",
        )

        Spacer(modifier = Modifier.height(8.dp))

//        Text(
//            text = user.description,
//            style = MaterialTheme.typography.body1,
//            textAlign = TextAlign.Center
//        )
    }
}

package org.mql.laktam.shortreads.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import org.mql.laktam.shortreads.models.User
import org.mql.laktam.shortreads.viewmodels.ProfileViewModel

@Composable
fun BottomNavigationBar(user: User,profileViewModel: ProfileViewModel,followingCurrentProfile: Boolean, isCurrentUser: Boolean, navController: NavController) {
    Box(modifier = Modifier.fillMaxSize()) {
        BottomAppBar(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth(),
            containerColor = Color.White,
            contentColor = Color.Gray,
            tonalElevation = 8.dp
        ) {
            IconButton(onClick = { navController.navigate("home") }) {
                Icon(
                    Icons.Default.Home,
                    contentDescription = "Home",
                    Modifier
                        .size(30.dp)
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            IconButton(onClick = { navController.navigate("expenses") }) {
                Icon(Icons.Default.Search, contentDescription = "Expenses", Modifier.size(30.dp))
            }
            Spacer(Modifier.weight(1f, true)) // Space for Fab
            IconButton(onClick = { navController.navigate("wallet") }) {
                Icon(Icons.Default.Notifications, contentDescription = "Wallet", Modifier.size(30.dp))
            }
            Spacer(modifier = Modifier.width(16.dp))
            IconButton(onClick = { navController.navigate("profile") }) {
                Icon(Icons.Default.Person, contentDescription = "Profile", Modifier.size(30.dp))
            }
        }

        FloatingActionButton(
            onClick = {
                if(isCurrentUser) {
                    navController.navigate("newPost/${user.username}")
                }else if(followingCurrentProfile){
                    profileViewModel.unfollow(user.username)
                }else{
                    profileViewModel.follow(user.username)
                }
            },
            containerColor = Color(0xFF0092ED),// 0C97ED
            shape = CircleShape,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .offset(y = (-60).dp) // Slightly above the BottomAppBar
//                .border(3.dp, Color.White, CircleShape)
        ) {
            val buttonText = if(isCurrentUser) {
                "new post"
            }else if(followingCurrentProfile){
                "unfollow"
            }else{
                "follow"
            }
            Text(
                text = buttonText,
                color = Color.White,
                modifier = Modifier.padding(horizontal = 10.dp)
            )
        }
    }
}
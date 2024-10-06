package org.mql.laktam.shortreads

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.mql.laktam.shortreads.auth.TokenManager
import org.mql.laktam.shortreads.ui.navigation.AppNavGraph
import org.mql.laktam.shortreads.ui.theme.ShortReadsTheme
import org.mql.laktam.shortreads.viewmodels.AuthViewModel
import org.mql.laktam.shortreads.viewmodels.PostsViewModel
import org.mql.laktam.shortreads.viewmodels.ProfileViewModel

class MainActivity : ComponentActivity() {
    private val authViewModel: AuthViewModel by viewModels()
    private lateinit var profileViewModel: ProfileViewModel
    private lateinit var postsViewModel: PostsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tokenManager = TokenManager(this)
        profileViewModel = ProfileViewModel(tokenManager)
        postsViewModel = PostsViewModel(tokenManager)
        enableEdgeToEdge()
        setContent {
            ShortReadsTheme {
//                Scaffold(
//                    bottomBar = {
//                        BottomAppBar(
//                            tonalElevation = 8.dp // optional for shadow effect
//                        ) {
//                            IconButton(onClick = { /* Navigate to Home */ }) {
//                                Icon(Icons.Default.Home, contentDescription = "Home")
//                            }
//                            Spacer(Modifier.weight(1f, true)) // Space for center item
//                            IconButton(onClick = { /* Navigate to Expenses */ }) {
//                                Icon(Icons.Default.Search, contentDescription = "Expenses")
//                            }
//                            Spacer(Modifier.weight(1f, true)) // Space between items
//                            IconButton(onClick = { /* Navigate to Profile */ }) {
//                                Icon(Icons.Default.Notifications, contentDescription = "Profile")
//                            }
//                            Spacer(Modifier.weight(1f, true)) // Space between items
//                            IconButton(onClick = { /* Navigate to Profile */ }) {
//                                Icon(Icons.Default.Person, contentDescription = "Profile")
//                            }
//                        }
//                    },
//                    floatingActionButton = {
//                        FloatingActionButton(onClick = { /* Add item action */ }) {
//                            Text("New post")
//                        }
//                    }
//                ) { paddingValues ->
//                    // Main content displayed above the bottom bar
//                    Box(modifier = Modifier.padding(paddingValues)) {
//                        AppNavGraph(authViewModel = authViewModel, profileViewModel = profileViewModel)
//                    }
//                }
                AppNavGraph(authViewModel = authViewModel, profileViewModel, postsViewModel)
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ShortReadsTheme {
//        SignupScreen()
    }
}
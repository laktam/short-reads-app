package org.mql.laktam.shortreads

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import org.mql.laktam.shortreads.auth.TokenManager
import org.mql.laktam.shortreads.ui.navigation.AppNavGraph
import org.mql.laktam.shortreads.ui.theme.ShortReadsTheme
import org.mql.laktam.shortreads.viewmodels.AuthViewModel
import org.mql.laktam.shortreads.viewmodels.ProfileViewModel

class MainActivity : ComponentActivity() {
    private val authViewModel: AuthViewModel by viewModels()
    private lateinit var profileViewModel: ProfileViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tokenManager = TokenManager(this)
        profileViewModel = ProfileViewModel(tokenManager)
        enableEdgeToEdge()
        setContent {
            ShortReadsTheme {
                AppNavGraph(authViewModel = authViewModel, profileViewModel)
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
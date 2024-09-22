package org.mql.laktam.shortreads.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.mql.laktam.shortreads.ui.screens.LoginScreen
import org.mql.laktam.shortreads.ui.screens.ProfileScreen
import org.mql.laktam.shortreads.ui.screens.SignupScreen
import org.mql.laktam.shortreads.viewmodels.AuthViewModel

@Composable
fun AppNavGraph(authViewModel: AuthViewModel) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "signup") {
        composable("signup") { SignupScreen(authViewModel, navController) }
        composable("login") { LoginScreen(authViewModel, navController) }
        composable("profile/{username}") { backStackEntry ->
            val username = backStackEntry.arguments?.getString("username") ?: ""
            ProfileScreen(username, navController)
        }
    }
}
package org.mql.laktam.shortreads.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.mql.laktam.shortreads.ui.screens.LoginScreen
import org.mql.laktam.shortreads.ui.screens.SignupScreen
import org.mql.laktam.shortreads.viewmodel.AuthViewModel

@Composable
fun AppNavGraph(authViewModel: AuthViewModel) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "login") {
        composable("signup") { SignupScreen(authViewModel, navController) }
        composable("login") { LoginScreen(authViewModel, navController) }
    }
}
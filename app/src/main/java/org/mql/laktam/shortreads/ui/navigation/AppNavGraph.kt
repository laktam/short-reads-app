package org.mql.laktam.shortreads.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.mql.laktam.shortreads.ui.screens.EditProfileScreen
import org.mql.laktam.shortreads.ui.screens.LoginScreen
import org.mql.laktam.shortreads.ui.screens.NewPostScreen
import org.mql.laktam.shortreads.ui.screens.ProfileScreen
import org.mql.laktam.shortreads.ui.screens.SignupScreen
import org.mql.laktam.shortreads.viewmodels.AuthViewModel
import org.mql.laktam.shortreads.viewmodels.PostsViewModel
import org.mql.laktam.shortreads.viewmodels.ProfileViewModel

@Composable
fun AppNavGraph(authViewModel: AuthViewModel, profileViewModel: ProfileViewModel, postsViewModel: PostsViewModel) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "login") {
        composable("newPost/{username}") {
                backStackEntry ->
            val username = backStackEntry.arguments?.getString("username") ?: ""
            NewPostScreen(navController, postsViewModel, username)
             }
        composable("signup") { SignupScreen(authViewModel, navController) }
        composable("editProfile") { EditProfileScreen(profileViewModel, navController) }
        composable("login") { LoginScreen(authViewModel,profileViewModel, navController) }
        composable("profile/{username}") { backStackEntry ->
            val username = backStackEntry.arguments?.getString("username") ?: ""
            ProfileScreen(username,profileViewModel, navController, postsViewModel)
        }
    }
}
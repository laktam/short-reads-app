package org.mql.laktam.shortreads.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import org.mql.laktam.shortreads.models.Post
import org.mql.laktam.shortreads.models.User
import org.mql.laktam.shortreads.viewmodels.PostsViewModel
import org.mql.laktam.shortreads.viewmodels.ProfileViewModel

@Composable
fun LastPosts(user: User, postsViewModel: PostsViewModel) {
    val lastPosts by postsViewModel.lastPosts.collectAsState()

    LaunchedEffect(user) {
        postsViewModel.loadLastPosts(user.username)
    }

    PostSlider(
        lastPosts
    )

}
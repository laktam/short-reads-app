package org.mql.laktam.shortreads.ui.components

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import org.mql.laktam.shortreads.models.Post
import org.mql.laktam.shortreads.models.User
import org.mql.laktam.shortreads.viewmodels.PostsViewModel
import org.mql.laktam.shortreads.viewmodels.ProfileViewModel

@Composable
fun LastPosts(user: User, postsViewModel: PostsViewModel) {
    PostSlider(
        listOf(
            Post("content", ""),
            Post("post text to try how is it gonna show on top of the background", ""),
            Post("post text to try how is it gonna show on top of the background", "url")
        )
    )

}
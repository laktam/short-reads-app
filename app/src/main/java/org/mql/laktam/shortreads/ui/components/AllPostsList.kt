package org.mql.laktam.shortreads.ui.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import org.mql.laktam.shortreads.models.Post
import org.mql.laktam.shortreads.viewmodels.PostsViewModel

@Composable
fun AllPostsList(postViewModel: PostsViewModel, username: String) {

    // Collect the posts StateFlow
    val posts by postViewModel.posts.collectAsState()

    LazyColumn  {
        items(posts) { post ->
            TextPostItem(post)
        }

        item {
            LaunchedEffect (Unit) {
                postViewModel.loadMorePosts(username)
            }
        }
    }
}

@Composable
fun TextPostItem(post: Post) {
    Text(text = post.content)
}

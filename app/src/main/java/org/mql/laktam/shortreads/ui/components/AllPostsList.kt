package org.mql.laktam.shortreads.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.mql.laktam.shortreads.models.Post
import org.mql.laktam.shortreads.viewmodels.PostsViewModel

@Composable
fun AllPostsList(postViewModel: PostsViewModel, username: String) {

//    // Collect the posts StateFlow
//    val posts by postViewModel.posts.collectAsState()
//    val isLastPage by postViewModel.isLastPage.collectAsState()
//
//    LazyColumn  {
//        items(posts) { post ->
//            TextPostItem(post)
//        }
//
////        item {
////            LaunchedEffect (Unit) {
////                postViewModel.loadMorePosts(username)
////            }
////        }
//        item {
//            if (!isLastPage) {
//                CircularProgressIndicator(modifier = Modifier.padding(16.dp))
//
//                // Trigger loading more posts when scrolled to the end
//                LaunchedEffect(Unit) {
//                    postViewModel.laodMore(username)
//                }
//            }
//        }
//    }
}

@Composable
fun TextPostItem(post: Post) {
    Text(text = post.content)
}

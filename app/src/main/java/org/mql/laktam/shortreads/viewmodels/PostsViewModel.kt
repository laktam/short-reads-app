package org.mql.laktam.shortreads.viewmodels

import android.content.Context
import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import org.mql.laktam.shortreads.auth.TokenManager
import org.mql.laktam.shortreads.models.Post
import org.mql.laktam.shortreads.repositories.PostRepositoryDefault
import org.mql.laktam.shortreads.utils.ImageMultipartUtils
import java.net.URI

class PostsViewModel(private val tokenManager: TokenManager): ViewModel() {
    private val postRepository = PostRepositoryDefault(tokenManager)
    private var backgroundImage: MultipartBody.Part? = null

    private val _posts = MutableStateFlow<List<Post>>(emptyList())
    val posts: StateFlow<List<Post>> get() = _posts

    private var page = 0
    private val pageSize = 5
    private var isLastPage = false

    fun loadMorePosts(username: String) {
        if (isLastPage) return

        viewModelScope.launch {
            val response = postRepository.getPosts(username, page, pageSize)
            _posts.value += response.content
            page++
            isLastPage = response.last
        }
    }

    fun onImageSelected(uri: Uri, context: Context) {
        val imageMultipartUtils = ImageMultipartUtils(context);
        backgroundImage = imageMultipartUtils.getMultipartBodyFromUri(uri)
    }

    fun newPost(username: String, content:String, onComplete: (username: String) -> Unit){
        viewModelScope.launch {
            try {
                val response = postRepository.newPost(username, content, backgroundImage)
                onComplete(username)
            }catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
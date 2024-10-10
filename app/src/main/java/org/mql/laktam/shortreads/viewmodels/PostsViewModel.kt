package org.mql.laktam.shortreads.viewmodels

import android.content.Context
import android.net.Uri
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
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
    val posts: StateFlow<List<Post>> = _posts.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    private var currentPage = 0
    private var isLastPage = false

    fun loadPosts(username: String, pageSize: Int = 5) {
        if (_isLoading.value || isLastPage) return

        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null

            try {
                val page = postRepository.getPosts(username, currentPage, pageSize)
                    _posts.value = _posts.value + page.content
                    currentPage++
                    isLastPage = page.last
            } catch (e: Exception) {
                e.printStackTrace()
                _error.value = "Error loading posts: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }

//    fun loadMorePosts(username: String) {
//        if (_isLastPage.value) return // Don't load more if it's the last page
//
//        viewModelScope.launch {
//            try{
//                val response = postRepository.getPosts(username, page, pageSize)
//                println("Load more posts response ::::: $response")
//                _posts.value += response.content
//                page++
//                _isLastPage.value = response.last
//            }catch (e: Exception) {
//                e.printStackTrace()
//            }
//
//        }
//    }

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
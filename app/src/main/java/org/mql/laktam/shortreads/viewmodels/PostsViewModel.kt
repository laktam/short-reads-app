package org.mql.laktam.shortreads.viewmodels

import android.content.Context
import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import org.mql.laktam.shortreads.auth.TokenManager
import org.mql.laktam.shortreads.repositories.PostRepositoryDefault
import org.mql.laktam.shortreads.utils.ImageMultipartUtils
import java.net.URI

class PostsViewModel(private val tokenManager: TokenManager): ViewModel() {
    val postRepository = PostRepositoryDefault(tokenManager)
    private var backgroundImage: MultipartBody.Part? = null


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
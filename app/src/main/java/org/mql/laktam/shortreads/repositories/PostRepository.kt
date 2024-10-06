package org.mql.laktam.shortreads.repositories

import okhttp3.MultipartBody
import org.mql.laktam.shortreads.models.MessageResponse

interface PostRepository {
    suspend fun newPost(username: String, content: String, backgroundImage: MultipartBody.Part?): MessageResponse

}
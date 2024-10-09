package org.mql.laktam.shortreads.repositories

import okhttp3.MultipartBody
import org.mql.laktam.shortreads.models.MessageResponse
import org.mql.laktam.shortreads.models.PageResponse
import org.mql.laktam.shortreads.models.Post

interface PostRepository {
    suspend fun newPost(username: String, content: String, backgroundImage: MultipartBody.Part?): MessageResponse
    suspend fun getPosts(username: String, page: Int, size: Int): PageResponse<Post>

}
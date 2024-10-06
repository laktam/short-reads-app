package org.mql.laktam.shortreads.repositories

import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.toRequestBody
import org.mql.laktam.shortreads.auth.RetrofitClient
import org.mql.laktam.shortreads.auth.TokenManager
import org.mql.laktam.shortreads.models.MessageResponse

class PostRepositoryDefault(private val tokenManager: TokenManager): PostRepository {
    private val apiService = RetrofitClient.apiService

    override suspend fun newPost(username: String, content: String, backgroundImage: MultipartBody.Part?): MessageResponse {
        val contentRequestBody = content.toRequestBody("text/plain".toMediaTypeOrNull())

        val response = apiService.newPost(username, contentRequestBody, backgroundImage, "Bearer ${tokenManager.getToken()}");
        if (response.isSuccessful) {
            return response.body() ?: throw Exception("post not added")
        }else{
            val errorMessage = response.errorBody()?.string() ?: "Unknown error"
            throw Exception(errorMessage)
        }
    }

}
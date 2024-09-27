package org.mql.laktam.shortreads.repositories

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.mql.laktam.shortreads.auth.RetrofitClient
import org.mql.laktam.shortreads.auth.TokenManager
import org.mql.laktam.shortreads.models.MessageResponse

class FollowRepositoryDefault(private val tokenManager: TokenManager): FollowRepository{
    private val apiService = RetrofitClient.apiService

    override suspend fun isFollowing(followerUsername: String, followedUsername: String): Boolean {
        return withContext(Dispatchers.IO) {
            val response = apiService.isFollowing(followerUsername, followedUsername, "Bearer ${tokenManager.getToken()}")
            if (response.isSuccessful) {
                response.body() ?: throw Exception("Unknown error")
            } else {
                val errorMessage = response.errorBody()?.string() ?: "Unknown error"
                throw Exception(errorMessage)
            }
        }
    }

    override suspend fun follow(followerUsername: String, followedUsername: String): MessageResponse {
        return withContext(Dispatchers.IO) {
            val response = apiService.follow(followerUsername, followedUsername, "Bearer ${tokenManager.getToken()}")
            if (response.isSuccessful) {
                response.body() ?: throw Exception("Unknown error")
            } else {
                val errorMessage = response.errorBody()?.string() ?: "Unknown error"
                throw Exception(errorMessage)
            }
        }
    }

    override suspend fun unfollow(followerUsername: String, followedUsername: String): MessageResponse {
        return withContext(Dispatchers.IO) {
            val response = apiService.unfollow(followerUsername, followedUsername, "Bearer ${tokenManager.getToken()}")
            if (response.isSuccessful) {
                response.body() ?: throw Exception("Unknown error")
            } else {
                val errorMessage = response.errorBody()?.string() ?: "Unknown error"
                throw Exception(errorMessage)
            }
        }
    }
}
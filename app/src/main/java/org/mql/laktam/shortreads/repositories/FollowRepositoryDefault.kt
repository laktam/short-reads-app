package org.mql.laktam.shortreads.repositories

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.mql.laktam.shortreads.auth.RetrofitClient
import org.mql.laktam.shortreads.auth.TokenManager

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
}
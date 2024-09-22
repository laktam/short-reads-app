package org.mql.laktam.shortreads.repositories


import org.mql.laktam.shortreads.auth.RetrofitClient
import org.mql.laktam.shortreads.models.User

class UserRepositoryDefault() : UserRepository {
    private val authApiService = RetrofitClient.authApiService

    override suspend fun getUserByUsername(username: String, token: String): User {
        val response =  authApiService.getUser(username, token)
        if (response.isSuccessful) {
            return response.body() ?: throw Exception("User not found")
        }else{
            val errorMessage = response.errorBody()?.string() ?: "Unknown error"
            throw Exception(errorMessage)
        }
    }

}
package org.mql.laktam.shortreads.repositories


import okhttp3.MultipartBody
import org.mql.laktam.shortreads.auth.RetrofitClient
import org.mql.laktam.shortreads.auth.TokenManager
import org.mql.laktam.shortreads.models.LoginResponse
import org.mql.laktam.shortreads.models.ProfileUpdateResponse
import org.mql.laktam.shortreads.models.User

class UserRepositoryDefault(private val tokenManager: TokenManager) : UserRepository {
    private val apiService = RetrofitClient.apiService

    override suspend fun getUserByUsername(username: String): User {
        println("username in UserRepository $username")
        println("token to getusername in repo ${tokenManager.getToken()}")
        val response =  apiService.getUser(username, "Bearer ${tokenManager.getToken()}")
        if (response.isSuccessful) {
            return response.body() ?: throw Exception("User not found")
        }else{
            val errorMessage = response.errorBody()?.string() ?: "Unknown error"
            throw Exception(errorMessage)
        }
    }

    override suspend fun updateUserProfile(username: String, user: User, profilePicture: MultipartBody.Part?): ProfileUpdateResponse {
        val token = tokenManager.getToken() ?: throw Exception("Token is missing")
        if(profilePicture != null){
            val response1 =  apiService.updateUserProfileImage(username, profilePicture, "Bearer $token")
        }
        val response2 =  apiService.updateUserProfile(username,user, "Bearer $token")
//        if(response2.isSuccessful){
//            return response2.body() ?:throw Exception("Profile not updated")
//        }else{
//            val errorMessage = response2.errorBody()?.string() ?: "Unknown error"
//            throw Exception(errorMessage)
//        }
        if (response2.isSuccessful) {
            val body = response2.body()
            if (body != null) {
                return ProfileUpdateResponse(body.token, body.username)
            } else {
                val errorMessage = response2.errorBody()?.string() ?: "Unknown error"
                throw Exception(errorMessage)
            }
        } else {
            throw Exception(response2.errorBody()?.string())
        }
    }

}
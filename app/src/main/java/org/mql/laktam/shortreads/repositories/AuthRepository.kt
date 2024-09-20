package org.mql.laktam.shortreads.repositories

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.mql.laktam.shortreads.auth.LoginRequest
import org.mql.laktam.shortreads.auth.RetrofitClient
import org.mql.laktam.shortreads.auth.SignupRequest
import org.mql.laktam.shortreads.models.LoginResponse
import org.mql.laktam.shortreads.models.SignupResponse

// Handles communication with the backend for authentication.
class AuthRepository {
    private val authApiService = RetrofitClient.authApiService

    suspend fun login(username: String, password: String): LoginResponse {
        return withContext(Dispatchers.IO) {
            val response = authApiService.login(LoginRequest(username, password))
            if (response.isSuccessful) {
                response.body() ?: throw Exception("Login failed")
            } else {
                val errorMessage = response.errorBody()?.string() ?: "Unknown error"
                throw Exception(errorMessage)
            }
        }
    }

    // withContext(Dispatchers.IO) used to perform network or I/O operation on a background thread
    suspend fun signup(name: String, email: String, password: String): SignupResponse {
        return withContext(Dispatchers.IO) {
            val response = authApiService.signup(SignupRequest(name, email, password))
            if (response.isSuccessful) {
                response.body() ?: throw Exception("Signup failed")
            } else {
                val errorMessage = response.errorBody()?.string() ?: "Unknown error"
                throw Exception(errorMessage)
            }
        }
    }
}
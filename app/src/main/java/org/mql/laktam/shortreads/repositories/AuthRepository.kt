package org.mql.laktam.shortreads.repositories

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.mql.laktam.shortreads.auth.RetrofitClient
import org.mql.laktam.shortreads.auth.SignupRequest
import org.mql.laktam.shortreads.models.User

// Handles communication with the backend for authentication.
class AuthRepository {
    private val authApiService = RetrofitClient.authApiService

    suspend fun login(email: String, password: String): User {
        // Call to backend API for login (e.g., Retrofit, FirebaseAuth)
        // Mocking a successful login for demonstration
        return User(email, "John Doe")
    }

    suspend fun signup(name: String, email: String, password: String): User {
        return withContext(Dispatchers.IO) {
            val response = authApiService.signup(SignupRequest(name, email, password))
            if (response.isSuccessful) {
                response.body() ?: throw Exception("Signup failed: No user returned")
            } else {
                throw Exception("Signup failed: ${response.message()}")
            }
        }
    }
}
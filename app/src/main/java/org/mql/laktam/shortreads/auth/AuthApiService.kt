package org.mql.laktam.shortreads.auth

import org.mql.laktam.shortreads.models.User
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.Response

data class SignupRequest(val username: String, val email: String, val password: String)

    interface AuthApiService {
        @POST("signup")
    suspend fun signup(@Body request: SignupRequest): Response<User>
}

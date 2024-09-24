package org.mql.laktam.shortreads.repositories

import android.content.Context
import okhttp3.MultipartBody
import org.mql.laktam.shortreads.models.LoginResponse
import org.mql.laktam.shortreads.models.ProfileUpdateResponse
import org.mql.laktam.shortreads.models.User


interface UserRepository {
    suspend fun getUserByUsername(username: String): User
    suspend fun updateUserProfile(username: String, user: User, profilePicture: MultipartBody.Part?): ProfileUpdateResponse
}
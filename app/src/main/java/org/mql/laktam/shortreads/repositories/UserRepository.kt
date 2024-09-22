package org.mql.laktam.shortreads.repositories

import android.content.Context
import org.mql.laktam.shortreads.models.User


interface UserRepository {
    suspend fun getUserByUsername(username: String, token: String): User
}
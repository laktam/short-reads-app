package org.mql.laktam.shortreads.auth

import android.content.Context
import android.content.SharedPreferences

class TokenManager(context: Context) {

    private val prefs: SharedPreferences =
        context.getSharedPreferences("auth_prefs", Context.MODE_PRIVATE)

    // Save the token
    fun saveToken(token: String) {
        prefs.edit().putString("auth_token", token).apply()
    }

    // Get the token
    fun getToken(): String? {
        return prefs.getString("auth_token", null)
    }

    // Clear the token
    fun clearToken() {
        prefs.edit().remove("auth_token").apply()
    }
}

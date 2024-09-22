package org.mql.laktam.shortreads.viewmodels

import android.content.Context
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.mql.laktam.shortreads.models.User
import org.mql.laktam.shortreads.repositories.UserRepositoryDefault

class ProfileViewModel : ViewModel() {
    private val userRepository = UserRepositoryDefault()
    private val _user = mutableStateOf<User?>(null)
    val user: State<User?> get() = _user


    fun loadUser(username: String, context: Context) {
        viewModelScope.launch {
            val token = getTokenFromSharedPreferences(context)
            _user.value = userRepository.getUserByUsername(username, "Bearer $token")
        }
    }

    private fun getTokenFromSharedPreferences(context: Context): String? {
        val sharedPreferences = context.getSharedPreferences("auth_prefs", Context.MODE_PRIVATE)
        return sharedPreferences.getString("jwt_token", null)
    }
}

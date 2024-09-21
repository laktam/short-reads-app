package org.mql.laktam.shortreads.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.mql.laktam.shortreads.models.AuthState
import org.mql.laktam.shortreads.repositories.AuthRepository

//Manages the signup logic and exposes state to the UI
class AuthViewModel() : ViewModel() {//private val authRepository: AuthRepository
    private val authRepository = AuthRepository()
    private val _authState = MutableLiveData<AuthState>()
    val authState: LiveData<AuthState> = _authState

    fun login(username: String, password: String, context: Context) {
        viewModelScope.launch {
            _authState.value = AuthState.Loading
            try {
                val response = authRepository.login(username, password)
                println("token ::::::::: ${response.token}")
                saveTokenToSharedPreferences(context, response.token)
                _authState.value = AuthState.Success("Login successful")
            } catch (e: Exception) {
                _authState.value = AuthState.Error("${e.message}")
            }
        }
    }

    fun signup(name: String, email: String, password: String) {
        viewModelScope.launch {
            _authState.value = AuthState.Loading
            try {
                delay(1000L)
                val response = authRepository.signup(name, email, password)
                _authState.value = AuthState.Success(response.message)
            } catch (e: Exception) {
                _authState.value = AuthState.Error("${e.message}")
            }
        }
    }

    // Function to reset the AuthState whenever the login or signup screen are composed
    fun resetState() {
        _authState.value = AuthState.Idle
    }

    fun saveTokenToSharedPreferences(context: Context, token: String) {
        val sharedPreferences = context.getSharedPreferences("auth_prefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("jwt_token", token)
        editor.apply()
    }

    fun getTokenFromSharedPreferences(context: Context): String? {
        val sharedPreferences = context.getSharedPreferences("auth_prefs", Context.MODE_PRIVATE)
        return sharedPreferences.getString("jwt_token", null)
    }
}



package org.mql.laktam.shortreads.viewmodels

import android.content.Context
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.mql.laktam.shortreads.auth.TokenManager
import org.mql.laktam.shortreads.models.AuthState
import org.mql.laktam.shortreads.repositories.AuthRepository

//Manages the signup logic and exposes state to the UI
class AuthViewModel() : ViewModel() {//private val authRepository: AuthRepository
    private val authRepository = AuthRepository()

    private var _authState = mutableStateOf<AuthState>(AuthState.Loading)
    val authState: State<AuthState> get() = _authState

    fun login(username: String, password: String, context: Context) {
        viewModelScope.launch {
            _authState.value = AuthState.Loading
            try {
                val response = authRepository.login(username, password)
                println("token ::::::::: ${response.token}")
                val tokenManager = TokenManager(context)
                tokenManager.saveToken(response.token)
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
//                delay(1000L)
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


}



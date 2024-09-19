package org.mql.laktam.shortreads.viewmodel

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

    fun signup(name: String, email: String, password: String) {
        viewModelScope.launch {
            _authState.value = AuthState.Loading
            try {
                delay(3000L)
                val user = authRepository.signup(name, email, password)
                _authState.value = AuthState.Success(user)
            } catch (e: Exception) {
                _authState.value = AuthState.Error("${e.message}")
            }
        }
    }
}



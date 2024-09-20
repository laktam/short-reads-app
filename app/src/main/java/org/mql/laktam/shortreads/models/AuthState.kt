package org.mql.laktam.shortreads.models

// Represents the different states of the authentication process.
sealed class AuthState {
    object Loading : AuthState()
    data class Success(val message: String) : AuthState()
    data class Error(val message: String) : AuthState()
}
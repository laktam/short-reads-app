package org.mql.laktam.shortreads.viewmodels

import android.content.Context
import android.net.Uri
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import org.mql.laktam.shortreads.auth.TokenManager
import org.mql.laktam.shortreads.models.User
import org.mql.laktam.shortreads.repositories.UserRepositoryDefault
import java.io.File
import java.io.FileOutputStream

class ProfileViewModel(private val tokenManager: TokenManager) : ViewModel() {
    private val userRepository = UserRepositoryDefault(tokenManager)
    private val _user = mutableStateOf<User?>(null)
    val user: State<User?> get() = _user // not always the logged in user, it is the last visited profile
    private var profilePicture: MultipartBody.Part? = null

    var followingCurrentProfile = mutableStateOf(false)
        private set

    var currentUsername = mutableStateOf("")
        private set

    fun updateCurrentUsername(newUsername: String) {
        currentUsername.value = newUsername
    }

    fun updatefollowingCurrentProfile(following: Boolean) {
        followingCurrentProfile.value = following
    }

    fun loadUser(username: String) {
        viewModelScope.launch {
            _user.value = userRepository.getUserByUsername(username)
        }
    }
    fun onImageSelected(uri: Uri, context: Context) {
        val imageFile = uriToFile(uri, context)
        profilePicture = createMultipartBody(imageFile)
    }

    fun updateUserProfile( username: String, user: User, onComplete: (username: String) -> Unit) {
        viewModelScope.launch {
            try {
                val profileUpdateResponse = userRepository.updateUserProfile(username, user, profilePicture)
                tokenManager.saveToken(profileUpdateResponse.token)
                updateCurrentUsername(profileUpdateResponse.username)
                loadUser(profileUpdateResponse.username)
                onComplete(profileUpdateResponse.username)
            } catch (e: Exception) {
                println("Error updating profile: ${e.message}")
                println("Error cause: ${e.cause}")
                e.printStackTrace()
            }
        }
    }

    // Utility function to convert URI to File
    private fun uriToFile(uri: Uri, context: Context): File {
        val contentResolver = context.contentResolver
        val file = File(context.cacheDir, "${System.currentTimeMillis()}.jpg")
        val inputStream = contentResolver.openInputStream(uri) ?: return file
        val outputStream = FileOutputStream(file)

        inputStream.copyTo(outputStream)
        inputStream.close()
        outputStream.close()

        return file
    }

    // Utility function to create MultipartBody.Part
    private fun createMultipartBody(file: File): MultipartBody.Part {
        val requestFile = file.asRequestBody("image/jpeg".toMediaTypeOrNull())
        return MultipartBody.Part.createFormData("file", file.name, requestFile)
    }


}

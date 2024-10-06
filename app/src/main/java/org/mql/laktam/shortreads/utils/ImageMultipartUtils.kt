package org.mql.laktam.shortreads.utils

import android.content.Context
import android.net.Uri
import android.webkit.MimeTypeMap
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import java.io.FileOutputStream

class ImageMultipartUtils(private val context: Context) {

    // Method to handle the whole process of converting URI to MultipartBody.Part
    fun getMultipartBodyFromUri(uri: Uri): MultipartBody.Part? {
        val imageFile = uriToFile(uri) ?: return null
        return createMultipartBody(imageFile)
    }

    // Utility function to convert URI to File
    private fun uriToFile(uri: Uri): File? {
        return try {
            val contentResolver = context.contentResolver
            val mimeType = contentResolver.getType(uri)
            val extension = MimeTypeMap.getSingleton().getExtensionFromMimeType(mimeType) ?: "jpg"

            val file = File(context.cacheDir, "${System.currentTimeMillis()}.$extension")
            val inputStream = contentResolver.openInputStream(uri) ?: return null
            val outputStream = FileOutputStream(file)

            inputStream.copyTo(outputStream)
            inputStream.close()
            outputStream.close()

            file
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    // Utility function to create MultipartBody.Part
    private fun createMultipartBody(file: File): MultipartBody.Part {
        val mimeType = MimeTypeMap.getFileExtensionFromUrl(file.path)
        val mediaType = mimeType?.let { MimeTypeMap.getSingleton().getMimeTypeFromExtension(it) }
        val requestFile: RequestBody = file.asRequestBody(mediaType?.toMediaTypeOrNull() ?: "image/jpeg".toMediaTypeOrNull())

        return MultipartBody.Part.createFormData("image", file.name, requestFile)
    }

//    // Utility function to create MultipartBody.Part
//    private fun createMultipartBody(file: File): MultipartBody.Part {
//        val requestFile: RequestBody = file.asRequestBody("image/jpeg".toMediaTypeOrNull())
//        return MultipartBody.Part.createFormData("image", file.name, requestFile)
//    }
}

package org.mql.laktam.shortreads.auth

import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.mql.laktam.shortreads.models.LoginResponse
import org.mql.laktam.shortreads.models.ProfileUpdateResponse
import org.mql.laktam.shortreads.models.MessageResponse
import org.mql.laktam.shortreads.models.Page
import org.mql.laktam.shortreads.models.Post
import org.mql.laktam.shortreads.models.User
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.PUT
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.Query

data class SignupRequest(val username: String, val email: String, val password: String)
data class LoginRequest(val username: String, val password: String)

interface ApiService {
    @POST("signup")
    suspend fun signup(@Body request: SignupRequest): Response<MessageResponse>

    @POST("login")
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>

    @GET("users/username/{username}")
    suspend fun getUser( @Path("username") username: String, @Header("Authorization") token: String): Response<User>

    @PUT("users/update/{username}")
    suspend fun updateUserProfile(
        @Path("username") username: String,
        @Body updatedUser: User,
        @Header("Authorization") token: String
    ): Response<ProfileUpdateResponse>

    @Multipart
    @POST("users/uploadProfilePicture/{username}")
    suspend fun updateUserProfileImage(@Path("username") username: String, @Part image: MultipartBody.Part? , @Header("Authorization") token: String): Response<MessageResponse>

    @GET("follows/isFollowing/{followerUsername}/{followedUsername}")
    suspend fun isFollowing( @Path("followerUsername") followerUsername: String,@Path("followedUsername") followedUsername: String, @Header("Authorization") token: String): Response<Boolean>

    @GET("follows/follow/{followerUsername}/{followedUsername}")
    suspend fun follow(@Path("followerUsername") followerUsername: String,@Path("followedUsername") followedUsername: String, @Header("Authorization") token: String): Response<MessageResponse>

    @GET("follows/unfollow/{followerUsername}/{followedUsername}")
    suspend fun unfollow(@Path("followerUsername") followerUsername: String,@Path("followedUsername") followedUsername: String, @Header("Authorization") token: String): Response<MessageResponse>

    @Multipart
    @POST("posts/new/{username}")
    suspend fun newPost(
        @Path("username") username: String,
        @Part("content") content: RequestBody,
        @Part image: MultipartBody.Part?,
        @Header("Authorization") token: String
    ): Response<MessageResponse>

    @GET("posts/user/pagination/{username}")
    suspend fun getPostsByUsername(
        @Path("username") username: String,
        @Query("page") page: Int,
        @Query("size") size: Int,
        @Header("Authorization") token: String
    ): Response<Page<Post>>

    @GET("posts/user/last/{username}")
    suspend fun getLastPostsByUsername(
        @Path("username") username: String,
        @Header("Authorization") token: String
    ): Response<Page<Post>>
}

package org.mql.laktam.shortreads.models

data class User(
    val profilePictureUrl: String,
    val username: String,
    val email: String,
    val description: String,
    val followersCount : Int,
)
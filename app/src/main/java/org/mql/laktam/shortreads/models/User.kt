package org.mql.laktam.shortreads.models

data class User(
    val username: String,
    val email: String,
    val description: String,
    val followersCount : Int,
)
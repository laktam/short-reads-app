package org.mql.laktam.shortreads.repositories

interface FollowRepository {
    suspend fun isFollowing(followerUsername: String, followedUsername: String): Boolean
}
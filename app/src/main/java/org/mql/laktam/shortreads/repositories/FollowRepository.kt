package org.mql.laktam.shortreads.repositories

import org.mql.laktam.shortreads.models.MessageResponse

interface FollowRepository {
    suspend fun isFollowing(followerUsername: String, followedUsername: String): Boolean
    suspend fun follow(followerUsername: String, followedUsername: String): MessageResponse
    suspend fun unfollow(followerUsername: String, followedUsername: String): MessageResponse
}
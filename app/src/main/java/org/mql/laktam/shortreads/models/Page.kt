package org.mql.laktam.shortreads.models

data class PageResponse<T>(
    val content: List<T>,
    val totalPages: Int,
    val totalElements: Long,
    val size: Int,
    val number: Int,
    val numberOfElements: Int,
    val last: Boolean,
    val first: Boolean,
    val empty: Boolean
)

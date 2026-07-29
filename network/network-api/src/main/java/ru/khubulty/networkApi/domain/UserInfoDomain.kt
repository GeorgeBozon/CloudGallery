package ru.khubulty.networkApi.domain

import kotlin.time.Instant


data class UserInfoDomain(
    val email: String?,
    val id: String,
    val createdAt: Instant?,
    val phone: String?
)
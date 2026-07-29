package ru.khubulty.networkApi.data

import kotlinx.coroutines.flow.Flow
import ru.khubulty.networkApi.domain.SessionState
import ru.khubulty.networkApi.domain.UserInfoDomain

interface AuthApi {
    suspend fun signUp(email: String, password: String): UserInfoDomain?

    suspend fun signIn(email: String, password: String)

    suspend fun resetPassword(email: String)

    fun getSessionProvider(): Flow<SessionState>
}
package ru.khubulty.networkImpl.data

import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.auth.providers.builtin.Email
import io.github.jan.supabase.auth.status.SessionStatus
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.khubulty.networkApi.data.AuthApi
import ru.khubulty.networkApi.domain.UserInfoDomain
import ru.khubulty.networkImpl.data.mapper.UserInfoMapper
import javax.inject.Inject
import ru.khubulty.networkApi.domain.SessionState

class AuthApiImpl @Inject constructor(private val auth: Auth, private val mapper: UserInfoMapper) :
    AuthApi {

    override suspend fun signUp(
        email: String,
        password: String
    ): UserInfoDomain? {
        val userData = auth.signUpWith(Email) {
            this.email = email
            this.password = password
        }
        return userData?.let {
            mapper.mapToDomainUserInfo(it)
        }
    }


    override suspend fun signIn(email: String, password: String) {
        auth.signInWith(Email) {
            this.email = email
            this.password = password
        }
    }

    override suspend fun resetPassword(email: String) {
        auth.resetPasswordForEmail(email)
    }

    override fun getSessionProvider(): Flow<SessionState> {
        return auth.sessionStatus.map {
            when (it) {
                is SessionStatus.Authenticated -> SessionState.Authenticated
                is SessionStatus.NotAuthenticated -> SessionState.NotAuthenticated
                is SessionStatus.Initializing -> SessionState.Loading
                is SessionStatus.RefreshFailure -> SessionState.Failure
            }
        }
    }
}
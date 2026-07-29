package ru.khubulty.authImpl.features.signup.data.repo

import ru.khubulty.authImpl.features.signup.domain.repo.SignUpRepository
import ru.khubulty.networkApi.data.AuthApi
import javax.inject.Inject

internal class SignUpRepositoryImpl @Inject constructor(private val authApi: AuthApi): SignUpRepository {
    override suspend fun signUp(email: String, password: String) {

    }
}
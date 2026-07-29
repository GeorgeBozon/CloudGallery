package ru.khubulty.authImpl.features.signup.domain.repo

interface SignUpRepository {
    suspend fun signUp(email: String, password: String)
}
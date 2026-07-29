package ru.khubulty.networkApi.domain

sealed class SessionState {
    object Loading: SessionState()

    object Authenticated: SessionState()

    object NotAuthenticated: SessionState()

    object Failure: SessionState()
}
package ru.khubulty.authApi

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
sealed interface LoginDestination: NavKey {
    @Serializable
    object WelcomeScreen: LoginDestination

    @Serializable
    object SighUpScreen: LoginDestination

    @Serializable
    object LogInScreen: LoginDestination
}
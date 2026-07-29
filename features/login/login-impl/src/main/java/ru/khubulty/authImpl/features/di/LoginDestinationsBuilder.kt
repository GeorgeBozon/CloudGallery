package ru.khubulty.authImpl.features.di

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import ru.khubulty.authApi.LoginDestination
import ru.khubulty.authImpl.features.signup.presentation.SignUpRoute
import ru.khubulty.authImpl.presentation.compose.WelcomeRoute
import ru.khubulty.navigationApi.Navigator

internal fun EntryProviderScope<NavKey>.loginDestinationsBuilder(navigator: Navigator){
    entry<LoginDestination.WelcomeScreen> {
        WelcomeRoute(
            signUp = { navigator.navigate(LoginDestination.SighUpScreen) },
            logIn = { navigator.navigate(LoginDestination.LogInScreen) }
        )
    }

    entry<LoginDestination.SighUpScreen> {
        SignUpRoute(
           onBack = navigator::goBack
        )
    }
}
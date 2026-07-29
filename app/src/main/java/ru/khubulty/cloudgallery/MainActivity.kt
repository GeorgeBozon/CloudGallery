package ru.khubulty.cloudgallery

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import dagger.hilt.android.AndroidEntryPoint
import ru.khubulty.authApi.LoginDestination
import ru.khubulty.cloudgallery.ui.theme.CloudGalleryTheme
import ru.khubulty.navigationApi.EntryProviderInstaller
import ru.khubulty.navigationApi.Navigator

import ru.khubulty.networkApi.data.AuthApi
import ru.khubulty.networkApi.domain.SessionState
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var navigator: Navigator

    @Inject
    lateinit var entryBuilders: Set<@JvmSuppressWildcards EntryProviderInstaller>

    @Inject
    lateinit var authApi: AuthApi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            CloudGalleryTheme {
                NavContent(authApi = authApi, navigator = navigator, entryBuilders = entryBuilders)
            }
        }
    }
}

@Composable
private fun NavContent(authApi: AuthApi, navigator: Navigator, entryBuilders: Set<EntryProviderInstaller>) {

    val sessionState by
    authApi.getSessionProvider().collectAsStateWithLifecycle(SessionState.Loading)

    LaunchedEffect(sessionState) {
        when (sessionState) {
            SessionState.NotAuthenticated -> navigator.replaceAll(LoginDestination.WelcomeScreen)
            else -> Unit

        }
    }

    NavDisplay(
        modifier = Modifier.fillMaxSize(),
        onBack = navigator::goBack,
        backStack = navigator.backStack,
        entryProvider = entryProvider {
           entryBuilders.forEach { builder ->
               this.builder(navigator)
           }
        })
}

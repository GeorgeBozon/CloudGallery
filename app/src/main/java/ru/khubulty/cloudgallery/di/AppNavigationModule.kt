package ru.khubulty.cloudgallery.di

import androidx.navigation3.runtime.NavKey
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import ru.khubulty.authApi.LoginDestination
import ru.khubulty.navigationImpl.InitialDestination

@Module
@InstallIn(ActivityRetainedComponent::class)
internal object AppNavigationModule {

    @Provides
    @InitialDestination
    fun provideInitialDestination(): NavKey = LoginDestination.WelcomeScreen
}